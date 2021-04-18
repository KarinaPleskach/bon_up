package com.bsuir.diploma.bonup.service.user.auth.impl;

import com.bsuir.diploma.bonup.dao.user.RoleRepository;
import com.bsuir.diploma.bonup.dao.user.UserLoginRepository;
import com.bsuir.diploma.bonup.dao.user.UserMailRepository;
import com.bsuir.diploma.bonup.dao.user.UserProfileRepository;
import com.bsuir.diploma.bonup.dto.converter.user.auth.RegUserDtoToUserLoginConverter;
import com.bsuir.diploma.bonup.dto.model.user.auth.EmailCodeDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.EmailDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.NewPasswordDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.RegUserDto;
import com.bsuir.diploma.bonup.exception.user.auth.AccessErrorException;
import com.bsuir.diploma.bonup.exception.user.auth.IncorrectLoginDataException;
import com.bsuir.diploma.bonup.exception.user.auth.InvalidEmailCodeException;
import com.bsuir.diploma.bonup.exception.user.auth.NoSuchAddressException;
import com.bsuir.diploma.bonup.exception.user.auth.NoSuchUserException;
import com.bsuir.diploma.bonup.exception.user.auth.NotValidateMailException;
import com.bsuir.diploma.bonup.exception.user.auth.RoleNotFoundException;
import com.bsuir.diploma.bonup.exception.user.auth.UserAlreadyExistException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.user.Role;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserMail;
import com.bsuir.diploma.bonup.model.user.UserProfile;
import com.bsuir.diploma.bonup.model.user.UserRole;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import com.bsuir.diploma.bonup.service.user.auth.RegistrationService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private UserMailRepository userMailRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private TranslationService translationService;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private RegUserDtoToUserLoginConverter regUserDtoToUserLoginConverter;

    @Override
    public void createNewUser(RegUserDto regUserDto, String lang) {
        validateRegUserDto(regUserDto, lang);
        saveUserAndProfile(regUserDto);
        sendCode(regUserDto.getLogin(), lang);
    }

    @Override
    public void checkMailCode(EmailCodeDto emailCodeDto, String lang) {
        validateEmailCodeDto(emailCodeDto, lang);
        UserMail userMail = userMailRepository.findByUserLogin(userLoginRepository.findByLogin(emailCodeDto.getEmail()).orElseThrow(() -> new NoSuchUserException(lang)))
                .orElseThrow(() -> new NoSuchUserException(lang));
        boolean isSuccess = emailCodeDto.getCode().equals(userMail.getMailCode());
        if (!isSuccess)
            throw new InvalidEmailCodeException(lang);
        else {
            userMail.setVerifyMail(true);
            userMail.setMailCode(null);
        }
    }

    @Override
    public String generateNewToken(String email, String lang) {
        UserLogin userLogin = userLoginRepository.findByLogin(email)
                .orElseThrow(() -> new NoSuchUserException(lang));

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("clientType", "user");
        tokenData.put("userID", userLogin.getId().toString());
        tokenData.put("username", userLogin.getLogin());
        tokenData.put("token_create_date", new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 100);
        tokenData.put("token_expiration_date", calendar.getTime());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        String key = "abc123";
        String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();

        userLogin.setToken(token);

        return token;
    }

    @Override
    public void resendCodeAndNullToken(EmailDto emailDto, String lang) {
        validateEmailDto(emailDto, lang);
        UserLogin userLogin = userLoginRepository.findByLogin(emailDto.getEmail())
                .orElseThrow(() -> new NoSuchUserException(lang));
        userLogin.setToken(null);
        UserMail userMail = userMailRepository.findByUserLogin(userLogin)
                .orElseThrow(() -> new NoSuchUserException(lang));
        userMail.setVerifyMail(false);
        sendCode(emailDto.getEmail(), lang);
    }

    @Override
    public void newPassword(NewPasswordDto newPassword, String lang) {
        validateNewPasswordDto(newPassword, lang);
        UserLogin userLogin = userLoginRepository.findByToken(newPassword.getToken())
                .orElseThrow(() -> new AccessErrorException(lang));

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        byte[] hashInBytes = md.digest(newPassword.getPassword().getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }

        userLogin.setPassword(sb.toString());
    }

    @Override
    public String generateNewTokenWithToken(String token, String lang) {
        UserLogin userLogin = userLoginRepository.findByToken(token)
                .orElseThrow(() -> new AccessErrorException(lang));

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("clientType", "user");
        tokenData.put("userID", userLogin.getId().toString());
        tokenData.put("username", userLogin.getLogin());
        tokenData.put("token_create_date", new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 100);
        tokenData.put("token_expiration_date", calendar.getTime());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        String key = "abc123";
        String newToken = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();

        userLogin.setToken(newToken);

        return newToken;
    }

    @Override
    public String login(RegUserDto userDto, String lang) {
        validateLoginUserDto(userDto, lang);
        UserLogin user = userLoginRepository.findByLogin(userDto.getLogin())
                .orElseThrow(() -> new NoSuchUserException(lang));
        UserMail userMail = userMailRepository.findByUserLogin(user)
                .orElseThrow(() -> new NoSuchUserException(lang));

        if (!userMail.isVerifyMail()) {
            throw new NotValidateMailException(lang);
        }

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        byte[] hashInBytes = md.digest(userDto.getPassword().getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }

        if (!sb.toString().equals(user.getPassword())) {
            throw new IncorrectLoginDataException(lang);
        }

        UserProfile profile = userProfileRepository.findByUserLogin(user)
                .orElseThrow(() -> new NoSuchUserException(lang));
        if (!userDto.getName().equals(profile.getName())) {
            throw new IncorrectLoginDataException(lang);
        }

        return user.getToken();
    }

    private void saveUserAndProfile(RegUserDto regUserDto) {
        UserLogin userLogin = regUserDtoToUserLoginConverter.convert(regUserDto);

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        byte[] hashInBytes = md.digest(userLogin.getPassword().getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        userLogin.setPassword(sb.toString());
        userLogin.setRoles(new HashSet<Role>(Arrays.asList(getStandardRole())));
        userLoginRepository.save(userLogin);

        UserProfile userProfile = UserProfile.builder()
                .name(regUserDto.getName())
                .userLogin(userLogin)
                .points(0L)
                .build();
        userProfileRepository.save(userProfile);

        UserMail userMail = UserMail.builder()
                .verifyMail(false)
                .mailCode(null)
                .userLogin(userLogin)
                .build();
        userMailRepository.save(userMail);
    }

    private void sendCode(String email, String lang) {
        int code = generateAndSaveMailCode(email, lang);

        SimpleMailMessage message = new SimpleMailMessage();

        String subject = translationService.getMessage("reg.email.subject", lang);
        String text = translationService.getMessage("reg.email.message", lang) + code;

        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);

        try {
            emailSender.send(message);
        } catch (MailException exception) {
            throw new NoSuchAddressException(lang);
        }
    }

    private void validateRegUserDto(RegUserDto regUserDto, String lang) {
        if (Objects.isNull(regUserDto)) {
            throw new NullValidationException(lang);
        }
        if (Objects.isNull(regUserDto.getLogin()) || Objects.isNull(regUserDto.getPassword()) || Objects.isNull(regUserDto.getName())) {
            throw new NullValidationException(lang);
        }
        if (userLoginRepository.findByLogin(regUserDto.getLogin()).isPresent()) {
            throw new UserAlreadyExistException(lang);
        }
    }

    private void validateEmailCodeDto(EmailCodeDto emailCodeDto, String lang) {
        if (Objects.isNull(emailCodeDto) || Objects.isNull(emailCodeDto.getCode()) || Objects.isNull(emailCodeDto.getEmail()))
            throw new NullValidationException(lang);
    }

    private void validateEmailDto(EmailDto emailDto, String lang) {
        if (Objects.isNull(emailDto) || Objects.isNull(emailDto.getEmail())) {
            throw new NullValidationException(lang);
        }
    }

    private void validateNewPasswordDto(NewPasswordDto newPasswordDto, String lang) {
        if (Objects.isNull(newPasswordDto) || Objects.isNull(newPasswordDto.getPassword()) || Objects.isNull(newPasswordDto.getToken()))
            throw new NullValidationException(lang);
    }

    private void validateLoginUserDto(RegUserDto userDto, String lang) {
        if (Objects.isNull(userDto) || Objects.isNull(userDto.getLogin()) || Objects.isNull(userDto.getName()) || Objects.isNull(userDto.getPassword()))
            throw new NullValidationException(lang);
    }

    private Role getStandardRole() {
        return roleRepository.findByDescription(UserRole.ROLE_USER)
                .orElseThrow(RoleNotFoundException::new);
    }

    private int generateAndSaveMailCode(String email, String lang) {
        int code = 1000 + (int) (Math.random() * (9999 - 1000 + 1));
        UserMail userMail = userMailRepository.findByUserLogin(userLoginRepository.findByLogin(email).orElseThrow(() -> new NoSuchUserException(lang)))
                .orElseThrow(() -> new NoSuchUserException(lang));
        userMail.setMailCode(String.valueOf(code));
        return code;
    }
}
