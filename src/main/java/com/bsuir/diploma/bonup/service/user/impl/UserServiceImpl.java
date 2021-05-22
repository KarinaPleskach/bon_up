package com.bsuir.diploma.bonup.service.user.impl;

import com.bsuir.diploma.bonup.dao.organization.EmployeeRepository;
import com.bsuir.diploma.bonup.dao.organization.OrganizationRepository;
import com.bsuir.diploma.bonup.dao.user.UserLoginRepository;
import com.bsuir.diploma.bonup.dto.converter.user.UserInfoDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.exception.user.auth.AccessErrorException;
import com.bsuir.diploma.bonup.exception.user.auth.NoSuchUserException;
import com.bsuir.diploma.bonup.exception.user.auth.RoleNotFoundException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.organization.Employee;
import com.bsuir.diploma.bonup.model.task.Coupon;
import com.bsuir.diploma.bonup.model.task.Task;
import com.bsuir.diploma.bonup.model.user.Role;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserProfile;
import com.bsuir.diploma.bonup.model.user.UserRole;
import com.bsuir.diploma.bonup.service.task.TaskService;
import com.bsuir.diploma.bonup.service.user.ProfileService;
import com.bsuir.diploma.bonup.service.user.UserService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ProfileService profileService;
    @Autowired
    private TaskService taskService;

    @Override
    public UserLogin findByToken(String token, String lang) {
        return userLoginRepository.findByToken(token)
                .orElseThrow(() -> new NoSuchUserException(lang));
    }

    @Override
    public UserLogin findByLogin(String login, String lang) {
        return userLoginRepository.findByLogin(login)
                .orElseThrow(() -> new NoSuchUserException(lang));
    }

    @Override
    public UserRole getUserRole(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        return getUserRole(tokenUser.getToken(), lang);
    }

    @Override
    public UserRole getUserRole(String token, String lang) {
        UserLogin user = findByToken(token, lang);
        return getUserRole(user);
    }

    @Override
    public UserRole getUserRole(UserLogin user) {
        Role role = user.getRoles().stream().findFirst()
                .orElseThrow(RoleNotFoundException::new);
        return role.getDescription();
    }

    private void validateTokenUser(TokenDto tokenUser, String lang) {
        if (Objects.isNull(tokenUser) || Objects.isNull(tokenUser.getToken())) {
            throw new NullValidationException(lang);
        }
    }

    @Override
    public UserInfoDto userInfo(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = findByToken(tokenUser.getToken(), lang);
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setLogin(user.getLogin());
        userInfoDto.setName(profileService.findByUserLogin(user, lang).getName());
        if (getUserRole(user).equals(UserRole.ROLE_EMPLOYEE)) {
            Employee employee = employeeRepository.findByUserLogin(user)
                    .orElseThrow(() -> new NoSuchUserException(lang));
            userInfoDto.setOrganizationName(employee.getOrganization().getName());
        } else if (getUserRole(user).equals(UserRole.ROLE_ORGANIZATION_ADMIN)) {
            String organization = organizationRepository.findByUserLogin(user).isEmpty() ? "-" :  organizationRepository.findByUserLogin(user).get(0).getName();
            userInfoDto.setOrganizationName(organization);
        }
        if (userInfoDto.getOrganizationName() == null)
            userInfoDto.setOrganizationName("-");
        UserProfile profile = profileService.findByUserLogin(user, lang);
        int done, coupons, balls, spent;
        if (profile.getDoneTasks().size() != 0)
            done = (int) (100 * profile.getDoneTasks().size() / profile.getAcceptedTasks().size());
        else
            done = 0;
        if (profile.getDoneCoupons().size() != 0)
            coupons = (int) (100 * profile.getDoneCoupons().size() / profile.getBoughtCoupons().size());
        else
            coupons = 0;
        if (taskService.getBalls(tokenUser, lang) != 0)
            balls = (int) (100 * taskService.getBalls(tokenUser, lang) / getAllBalls(user, lang));
        else
            balls = 0;
        if (spentBalls(user, lang) != 0)
            spent = (int) (100 * spentBalls(user, lang) / getAllBalls(user, lang));
        else
            spent = 0;

        userInfoDto.setDonePercent((long) done);
        userInfoDto.setCouponsPercent((long) coupons);
        userInfoDto.setBallsPercent((long) balls);
        userInfoDto.setSpentBallsPercent((long) spent);

        userInfoDto.setBalls(taskService.getBalls(tokenUser, lang));
        userInfoDto.setSpentBalls((long) spentBalls(user, lang));
        userInfoDto.setTasksNumber((long) profile.getDoneTasks().size());

//        if (getAllBalls(user, lang) >= 1000) {
//            Progress progress = progressRepository.findByNameKey("goal.1");
//            String name = translationService.getMessage(progress.getNameKey(), lang);
//            String desc = translationService.getMessage(progress.getDescriptionKey(), lang);
//            userInfoDto.getGoals().add(new NameDescription(name, desc, true));
//        } else {
//            Progress progress = progressRepository.findByNameKey("goal.1");
//            String name = translationService.getMessage(progress.getNameKey(), lang);
//            String desc = translationService.getMessage(progress.getDescriptionKey(), lang);
//            userInfoDto.getGoals().add(new NameDescription(name, desc, false));
//        }
//        if (spentBalls(user, lang) >= 1000) {
//            Progress progress = progressRepository.findByNameKey("goal.2");
//            String name = translationService.getMessage(progress.getNameKey(), lang);
//            String desc = translationService.getMessage(progress.getDescriptionKey(), lang);
//            userInfoDto.getGoals().add(new NameDescription(name, desc, true));
//        } else {
//            Progress progress = progressRepository.findByNameKey("goal.2");
//            String name = translationService.getMessage(progress.getNameKey(), lang);
//            String desc = translationService.getMessage(progress.getDescriptionKey(), lang);
//            userInfoDto.getGoals().add(new NameDescription(name, desc, false));
//        }
//        if (profile.getDoneTasks().size() >= 100) {
//            Progress progress = progressRepository.findByNameKey("goal.3");
//            String name = translationService.getMessage(progress.getNameKey(), lang);
//            String desc = translationService.getMessage(progress.getDescriptionKey(), lang);
//            userInfoDto.getGoals().add(new NameDescription(name, desc, true));
//        } else {
//            Progress progress = progressRepository.findByNameKey("goal.3");
//            String name = translationService.getMessage(progress.getNameKey(), lang);
//            String desc = translationService.getMessage(progress.getDescriptionKey(), lang);
//            userInfoDto.getGoals().add(new NameDescription(name, desc, false));
//
//        }
        return userInfoDto;
    }

    @Override
    public int getAllBalls(UserLogin user, String lang) {
        checkPermission(user, lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
        int sum = 0;
//        for (Task task : profile.getDoneTasks()) {
//            sum += task.getType().getPointsCount();
//        }
        return sum;
    }

    @Override
    public int spentBalls(UserLogin user, String lang) {
//        checkPermission(user, lang);
//        UserProfile profile = profileService.findByUserLogin(user, lang);
//        int sum = 0;
//        for (Coupon coupon : profile.getBoughtCoupons()) {
//            sum += coupon.getType().getCostCount();
//        }
//        return sum;
        return 0;
    }

    @Override
    public void checkPermission(UserLogin user, String lang) {
        if (!getUserRole(user).equals(UserRole.ROLE_USER))
            throw new AccessErrorException(lang);
    }

}
