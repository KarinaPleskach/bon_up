package com.bsuir.diploma.bonup.service.admin.organization.impl;

import com.bsuir.diploma.bonup.dao.organization.ContractRepository;
import com.bsuir.diploma.bonup.dao.organization.NumberOfFacilitiesRepository;
import com.bsuir.diploma.bonup.dao.organization.OrganizationRepository;
import com.bsuir.diploma.bonup.dao.user.RoleRepository;
import com.bsuir.diploma.bonup.dao.user.UserLoginRepository;
import com.bsuir.diploma.bonup.dao.user.UserMailRepository;
import com.bsuir.diploma.bonup.dao.user.UserProfileRepository;
import com.bsuir.diploma.bonup.dto.converter.organization.ContractToAllContractDtoConverter;
import com.bsuir.diploma.bonup.dto.converter.user.auth.AdminAuthUserToUserConverter;
import com.bsuir.diploma.bonup.dto.model.organization.AllContractDto;
import com.bsuir.diploma.bonup.dto.model.organization.admin.AdminNameDto;
import com.bsuir.diploma.bonup.dto.model.organization.admin.ContractDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.AdminAuthUser;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.NewOrganization;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.OldAdminAuthUser;
import com.bsuir.diploma.bonup.exception.organization.NoSuchContractException;
import com.bsuir.diploma.bonup.exception.organization.NoSuchOrganizationException;
import com.bsuir.diploma.bonup.exception.organization.OrganizationWithSuchNameAlreadyExistException;
import com.bsuir.diploma.bonup.exception.user.auth.AccessErrorException;
import com.bsuir.diploma.bonup.exception.user.auth.NoSuchUserException;
import com.bsuir.diploma.bonup.exception.user.auth.RoleNotFoundException;
import com.bsuir.diploma.bonup.exception.user.auth.UserAlreadyExistException;
import com.bsuir.diploma.bonup.exception.validation.DateToBeforeException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.organization.Contract;
import com.bsuir.diploma.bonup.model.organization.NumberOfFacilities;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.user.Role;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserMail;
import com.bsuir.diploma.bonup.model.user.UserProfile;
import com.bsuir.diploma.bonup.model.user.UserRole;
import com.bsuir.diploma.bonup.service.additional.CityService;
import com.bsuir.diploma.bonup.service.admin.organization.AdminOrganizationService;
import com.bsuir.diploma.bonup.service.user.UserService;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminOrganizationServiceImpl implements AdminOrganizationService {

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;

    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserMailRepository userMailRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private NumberOfFacilitiesRepository numberOfFacilitiesRepository;
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private AdminAuthUserToUserConverter adminAuthUserToUserConverter;
    @Autowired
    private ContractToAllContractDtoConverter contractToAllContractDtoConverter;

    @Override
    public String createNewUser(AdminAuthUser adminAuthUser, String lang) {
        checkAdminPermission(adminAuthUser, lang);
        validateAdminAuthUser(adminAuthUser, lang);
        return saveUserAndProfile(adminAuthUser);
    }

    @Override
    public String getUserOrganizationToken(OldAdminAuthUser oldAdminAuthUser, String lang) {
        checkAdminPermission(oldAdminAuthUser, lang);
        validateOldAdminAuthUser(oldAdminAuthUser, lang);
        UserLogin user = userService.findByLogin(oldAdminAuthUser.getLogin(), lang);
        return user.getToken();
    }

    @Override
    public void createOrganizationForUser(NewOrganization newOrganization, String lang) {
        checkAdminPermission(newOrganization, lang);
        validateNewOrganization(newOrganization, lang);
        saveOrganization(newOrganization, lang);
    }

    @Override
    public void setContract(ContractDto contractDto, String lang) {
        checkAdminPermission(contractDto, lang);
        Organization organization = validateContractDto(contractDto, lang);

        NumberOfFacilities numberOfFacilities = NumberOfFacilities.builder()
                .stocks(contractDto.getStocks())
                .easyCoupons(contractDto.getEasyCoupons())
                .easyTasks(contractDto.getEasyTasks())
                .heavyCoupons(contractDto.getHeavyCoupons())
                .heavyTasks(contractDto.getHeavyTasks())
                .mediumCoupons(contractDto.getMediumCoupons())
                .mediumTasks(contractDto.getMediumTasks())
                .build();
        numberOfFacilitiesRepository.save(numberOfFacilities);

        Contract contract = Contract.builder()
                .cost(null)
                .dateFrom(contractDto.getDateFrom())
                .dateTo(contractDto.getDateTo())
                .organization(organization)
                .numberOfFacilities(numberOfFacilities)
                .paid(true)
                .build();
        contractRepository.save(contract);
    }

    @Override
    public void updateContract(ContractDto contractDto, Long contractId, String lang) {
        checkAdminPermission(contractDto, lang);
        Organization organization = validateContractDto(contractDto, lang);
        Contract contract = contractRepository.findByOrganizationAndId(organization, contractId)
                .orElseThrow(() -> new NoSuchContractException(lang));
        NumberOfFacilities numberOfFacilities = contract.getNumberOfFacilities();

        contract.setDateFrom(contractDto.getDateFrom());
        contract.setDateTo(contractDto.getDateTo());
        numberOfFacilities.setStocks(contractDto.getStocks());
        numberOfFacilities.setEasyCoupons(contractDto.getEasyCoupons());
        numberOfFacilities.setMediumCoupons(contractDto.getMediumCoupons());
        numberOfFacilities.setHeavyCoupons(contractDto.getHeavyCoupons());
        numberOfFacilities.setEasyTasks(contractDto.getEasyTasks());
        numberOfFacilities.setMediumTasks(contractDto.getMediumTasks());
        numberOfFacilities.setHeavyTasks(contractDto.getHeavyTasks());
    }

    @Override
    public List<AllContractDto> getContracts(AdminNameDto adminNameDto, String lang) {
        checkAdminPermission(adminNameDto, lang);
        if (Objects.isNull(adminNameDto.getName())) {
            throw new NullValidationException(lang);
        }
        Organization organization = null;
//                organizationRepository.findByName(adminNameDto.getName())
//                .orElseThrow(() -> new NoSuchOrganizationException(lang));
        return contractRepository.findByOrganization(organization)
                .stream()
                .map(contract -> contractToAllContractDtoConverter.convert(contract))
                .collect(Collectors.toList());
    }

    private void saveOrganization(NewOrganization newOrganization, String lang) {
        UserLogin user = userService.findByToken(newOrganization.getUserOrganizationToken(), lang);
        Organization organization = new Organization(newOrganization.getName(), cityService.get(newOrganization.getCityId(), lang),
                newOrganization.getX(), newOrganization.getY(), user);
        organizationRepository.save(organization);
    }

    private String saveUserAndProfile(AdminAuthUser userDto) {
        UserLogin user = adminAuthUserToUserConverter.convert(userDto);

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        byte[] hashInBytes = md.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }

        user.setPassword(sb.toString());
        user.setRoles(new HashSet<Role>(Arrays.asList(getAdminOrganizationRole())));

        userLoginRepository.save(user);

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("clientType", "user");
        tokenData.put("userID", user.getId().toString());
        tokenData.put("username", user.getLogin());
        tokenData.put("token_create_date", new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 100);
        tokenData.put("token_expiration_date", calendar.getTime());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        String key = "abc123";
        String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();

        user.setToken(token);

        UserProfile profile = UserProfile.builder()
                .name(userDto.getName())
                .userLogin(user)
                .points(0L)
                .build();
        userProfileRepository.save(profile);

        UserMail userMail = UserMail.builder()
                .verifyMail(true)
                .mailCode(null)
                .userLogin(user)
                .build();
        userMailRepository.save(userMail);

        return token;
    }

    private void checkAdminPermission(AdminAuthUser adminAuthUser, String lang) {
        if (Objects.isNull(adminAuthUser) || Objects.isNull(adminAuthUser.getAdminToken()))
            throw new NullValidationException(lang);
        checkAdminPermission(adminAuthUser.getAdminToken(), lang);
    }

    private void checkAdminPermission(String adminToken, String lang) {
        if (!userService.getUserRole(adminToken, lang).equals(UserRole.ROLE_GLOBAL_ADMIN))
            throw new AccessErrorException(lang);
    }

    private void checkAdminPermission(OldAdminAuthUser oldAdminAuthUser, String lang) {
        if (Objects.isNull(oldAdminAuthUser) || Objects.isNull(oldAdminAuthUser.getAdminToken()))
            throw new NullValidationException(lang);
        checkAdminPermission(oldAdminAuthUser.getAdminToken(), lang);
    }

    private void checkAdminPermission(NewOrganization newOrganization, String lang) {
        if (Objects.isNull(newOrganization) || Objects.isNull(newOrganization.getAdminToken()))
            throw new NullValidationException(lang);
        checkAdminPermission(newOrganization.getAdminToken(), lang);
    }

    private void checkAdminPermission(ContractDto contractDto, String lang) {
        if (Objects.isNull(contractDto) || Objects.isNull(contractDto.getAdminToken()))
            throw new NullValidationException(lang);
        checkAdminPermission(contractDto.getAdminToken(), lang);
    }

    private void checkAdminPermission(AdminNameDto adminNameDto, String lang) {
        if (Objects.isNull(adminNameDto) || Objects.isNull(adminNameDto.getAdminToken()))
            throw new NullValidationException(lang);
        checkAdminPermission(adminNameDto.getAdminToken(), lang);
    }

    private void validateAdminAuthUser(AdminAuthUser authUserDto, String lang) {
        if (Objects.isNull(authUserDto.getLogin()) || Objects.isNull(authUserDto.getPassword()) || Objects.isNull(authUserDto.getName())) {
            throw new NullValidationException(lang);
        }
        if (userLoginRepository.findByLogin(authUserDto.getLogin()).isPresent()) {
            throw new UserAlreadyExistException(lang);
        }
    }

    private void validateOldAdminAuthUser(OldAdminAuthUser oldAdminAuthUser, String lang) {
        if (Objects.isNull(oldAdminAuthUser.getLogin())) {
            throw new NullValidationException(lang);
        }
    }

    private void validateNewOrganization(NewOrganization newOrganization, String lang) {
        if (Objects.isNull(newOrganization.getName()) || Objects.isNull(newOrganization.getX()) || Objects.isNull(newOrganization.getY()) || Objects.isNull(newOrganization.getCityId()) || Objects.isNull(newOrganization.getUserOrganizationToken())) {
            throw new NullValidationException(lang);
        }
//        if (organizationRepository.findByName(newOrganization.getName()).isPresent()) {
//            throw new OrganizationWithSuchNameAlreadyExistException(lang);
//        }
        if (!userLoginRepository.findByToken(newOrganization.getUserOrganizationToken()).isPresent()) {
            throw new NoSuchUserException(lang);
        }
        if (!userService.getUserRole(newOrganization.getUserOrganizationToken(), lang).equals(UserRole.ROLE_ORGANIZATION_ADMIN)) {
            throw new AccessErrorException(lang);
        }
    }

    private Organization validateContractDto(ContractDto contractDto, String lang) {
        if (Objects.isNull(contractDto.getName()) || Objects.isNull(contractDto.getAdminToken()) || Objects.isNull(contractDto.getUserOrganizationToken())
                || Objects.isNull(contractDto.getDateTo()) || Objects.isNull(contractDto.getDateFrom()) || Objects.isNull(contractDto.getStocks())
                || Objects.isNull(contractDto.getHeavyTasks()) || Objects.isNull(contractDto.getMediumTasks()) || Objects.isNull(contractDto.getEasyTasks())
                || Objects.isNull(contractDto.getHeavyCoupons()) || Objects.isNull(contractDto.getMediumCoupons()) || Objects.isNull(contractDto.getEasyCoupons())) {
            throw new NullValidationException(lang);
        }
        if (!userLoginRepository.findByToken(contractDto.getUserOrganizationToken()).isPresent()) {
            throw new NoSuchUserException(lang);
        }
        if (!userService.getUserRole(contractDto.getUserOrganizationToken(), lang).equals(UserRole.ROLE_ORGANIZATION_ADMIN)) {
            throw new AccessErrorException(lang);
        }
        if (contractDto.getDateTo().before(contractDto.getDateFrom()))
            throw new DateToBeforeException(lang);

        UserLogin userLogin = userService.findByToken(contractDto.getUserOrganizationToken(), lang);
//        return organizationRepository.findByNameAndUserLogin(contractDto.getName(), userLogin)
//                .orElseThrow(() -> new NoSuchOrganizationException(lang));
        return null;
    }

    private Role getAdminOrganizationRole() {
        return roleRepository.findByDescription(UserRole.ROLE_ORGANIZATION_ADMIN)
                .orElseThrow(RoleNotFoundException::new);
    }

}
