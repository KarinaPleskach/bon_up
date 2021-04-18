package com.bsuir.diploma.bonup.service.organization.employee.impl;

import com.bsuir.diploma.bonup.dao.organization.EmployeeRepository;
import com.bsuir.diploma.bonup.dao.user.RoleRepository;
import com.bsuir.diploma.bonup.dao.user.UserLoginRepository;
import com.bsuir.diploma.bonup.dao.user.UserMailRepository;
import com.bsuir.diploma.bonup.dao.user.UserProfileRepository;
import com.bsuir.diploma.bonup.dto.converter.organization.employee.NewEmployeeDtoToUserLoginConverter;
import com.bsuir.diploma.bonup.dto.model.organization.employee.NewEmployeeDto;
import com.bsuir.diploma.bonup.exception.user.auth.AccessErrorException;
import com.bsuir.diploma.bonup.exception.user.auth.NoSuchUserException;
import com.bsuir.diploma.bonup.exception.user.auth.RoleNotFoundException;
import com.bsuir.diploma.bonup.exception.user.auth.UserAlreadyExistException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.organization.Employee;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.user.Role;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserMail;
import com.bsuir.diploma.bonup.model.user.UserProfile;
import com.bsuir.diploma.bonup.model.user.UserRole;
import com.bsuir.diploma.bonup.service.organization.OrganizationService;
import com.bsuir.diploma.bonup.service.organization.employee.EmployeeService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserMailRepository userMailRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private NewEmployeeDtoToUserLoginConverter newEmployeeDtoToUserLoginConverter;

    @Override
    public List<Employee> findByOrganization(Organization organization) {
        return employeeRepository.findByOrganization(organization);
    }

    @Override
    public Employee findByUserLogin(UserLogin userLogin, String lang) {
        return employeeRepository.findByUserLogin(userLogin)
                .orElseThrow(() -> new NoSuchUserException(lang));
    }

    @Override
    public void createEmployeeUser(NewEmployeeDto employeeDto, String lang) {
        checkOrganizationPermission(employeeDto, lang);
        if (userLoginRepository.findByLogin(employeeDto.getLogin()).isPresent()) {
            throw new UserAlreadyExistException(lang);
        }

        UserLogin orgAdminUser = userService.findByToken(employeeDto.getToken(), lang);
        Organization organization = organizationService.findByNameAndUser(employeeDto.getOrganizationName(), orgAdminUser, lang);

        UserLogin user = newEmployeeDtoToUserLoginConverter.convert(employeeDto);

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
        user.setRoles(new HashSet<Role>(Arrays.asList(getEmployeeRole())));

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
                .name(employeeDto.getName())
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

        Employee employee = new Employee(user, organization);
        employeeRepository.save(employee);
    }

    private Role getEmployeeRole() {
        return roleRepository.findByDescription(UserRole.ROLE_EMPLOYEE)
                .orElseThrow(RoleNotFoundException::new);
    }

    private void checkOrganizationPermission(NewEmployeeDto employeeDto, String lang) {
        if (Objects.isNull(employeeDto) || Objects.isNull(employeeDto.getToken()) || Objects.isNull(employeeDto.getOrganizationName())
                || Objects.isNull(employeeDto.getName()) || Objects.isNull(employeeDto.getLogin()) || Objects.isNull(employeeDto.getPassword()))
            throw new NullValidationException(lang);
        checkOrganizationPermission(employeeDto.getToken(), lang);
    }

    private void checkOrganizationPermission(String token, String lang) {
        if (!userService.getUserRole(token, lang).equals(UserRole.ROLE_ORGANIZATION_ADMIN))
            throw new AccessErrorException(lang);
    }

}
