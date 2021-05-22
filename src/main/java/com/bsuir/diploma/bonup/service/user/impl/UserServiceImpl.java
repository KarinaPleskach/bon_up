package com.bsuir.diploma.bonup.service.user.impl;

import com.bsuir.diploma.bonup.dao.organization.EmployeeRepository;
import com.bsuir.diploma.bonup.dao.organization.OrganizationNewDao;
import com.bsuir.diploma.bonup.dao.organization.OrganizationRepository;
import com.bsuir.diploma.bonup.dao.user.UserLoginRepository;
import com.bsuir.diploma.bonup.dto.converter.user.Goal;
import com.bsuir.diploma.bonup.dto.converter.user.UserInfoDto;
import com.bsuir.diploma.bonup.dto.model.IdToken;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.exception.photo.PhotoAlreadyExistException;
import com.bsuir.diploma.bonup.exception.user.auth.AccessErrorException;
import com.bsuir.diploma.bonup.exception.user.auth.NoSuchUserException;
import com.bsuir.diploma.bonup.exception.user.auth.RoleNotFoundException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.organization.Employee;
import com.bsuir.diploma.bonup.model.organization.OrganizationNew;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.Coupon;
import com.bsuir.diploma.bonup.model.task.CouponNew;
import com.bsuir.diploma.bonup.model.task.Task;
import com.bsuir.diploma.bonup.model.task.TaskNew;
import com.bsuir.diploma.bonup.model.user.Role;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserProfile;
import com.bsuir.diploma.bonup.model.user.UserRole;
import com.bsuir.diploma.bonup.service.photo.PhotoService;
import com.bsuir.diploma.bonup.service.task.CouponService;
import com.bsuir.diploma.bonup.service.task.TaskService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import com.bsuir.diploma.bonup.service.user.ProfileService;
import com.bsuir.diploma.bonup.service.user.UserService;
import java.util.Collections;
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
    private OrganizationNewDao organizationNewDao;

    @Autowired
    private ProfileService profileService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private TranslationService translationService;
    @Autowired
    private PhotoService photoService;

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
        userInfoDto.setEmail(user.getLogin());
        userInfoDto.setName(profileService.findByUserLogin(user, lang).getName());
        StringBuilder organizationName = new StringBuilder();
        if (organizationNewDao.findByUserLogin(user).size() != 0) {
            organizationName = new StringBuilder(organizationNewDao.findByUserLogin(user).get(0).getTitle());
        }
        for (int i = 1; i < organizationNewDao.findByUserLogin(user).size(); i++) {
            organizationName.append(", ");
            organizationName.append(organizationNewDao.findByUserLogin(user).get(i).getTitle());
        }
        userInfoDto.setOrganizationName(organizationName.toString());
        if (userInfoDto.getOrganizationName() == null)
            userInfoDto.setOrganizationName("-");
        UserProfile profile = profileService.findByUserLogin(user, lang);
        if (profile.getPhotos().size() != 0) {
            userInfoDto.setPhotoId(profile.getPhotos().get(0).getId());
        }
//        int done, coupons, balls, spent;
//        if (profile.getDoneTasks().size() != 0)
//            done = (int) (100 * profile.getDoneTasks().size() / profile.getAcceptedTasks().size());
//        else
//            done = 0;
//        if (profile.getDoneCoupons().size() != 0)
//            coupons = (int) (100 * profile.getDoneCoupons().size() / profile.getBoughtCoupons().size());
//        else
//            coupons = 0;
//        if (taskService.getBalls(tokenUser, lang) != 0)
//            balls = (int) (100 * taskService.getBalls(tokenUser, lang) / getAllBalls(user, lang));
//        else
//            balls = 0;
//        if (spentBalls(user, lang) != 0)
//            spent = (int) (100 * spentBalls(user, lang) / getAllBalls(user, lang));
//        else
//            spent = 0;

//        userInfoDto.setDonePercent((long) done);
//        userInfoDto.setCouponsPercent((long) coupons);
//        userInfoDto.setBallsPercent((long) balls);
//        userInfoDto.setSpentBallsPercent((long) spent);

        userInfoDto.setBalls(taskService.getBalls(tokenUser, lang));
        userInfoDto.setSpentBalls((long) spentBalls(user, lang));
        userInfoDto.setTasksNumber((long) profile.getDoneTasks().size());

        userInfoDto.setFinishedTasks(taskService.getDoneTasks(tokenUser, lang));
        userInfoDto.setFinishedCoupons(couponService.getBoughtCoupons(tokenUser, lang));

        Goal g1 = new Goal();
        g1.setName(translationService.getMessage("goal.1", lang));
        g1.setDescription(translationService.getMessage("goal.1.desc", lang));
        g1.setFlag(false);

        Goal g2 = new Goal();
        g2.setName(translationService.getMessage("goal.2", lang));
        g2.setDescription(translationService.getMessage("goal.2.desc", lang));
        g2.setFlag(false);

        Goal g3 = new Goal();
        g3.setName(translationService.getMessage("goal.3", lang));
        g3.setDescription(translationService.getMessage("goal.3.desc", lang));
        g3.setFlag(false);

        userInfoDto.getGoals().add(g1);
        userInfoDto.getGoals().add(g2);
        userInfoDto.getGoals().add(g3);

        if (getAllBalls(user, lang) >= 1000) {
            userInfoDto.getGoals().get(0).setFlag(true);
        }
        if (spentBalls(user, lang) >= 1000) {
            userInfoDto.getGoals().get(1).setFlag(true);
        }
        if (profile.getDoneTasks().size() >= 100) {
            userInfoDto.getGoals().get(2).setFlag(true);
        }
        return userInfoDto;
    }

    @Override
    public int getAllBalls(UserLogin user, String lang) {
        checkPermission(user, lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
        int sum = 0;
        for (TaskNew task : profile.getDoneTasks()) {
            sum += task.getBonus();
        }
        return sum;
    }

    @Override
    public int spentBalls(UserLogin user, String lang) {
        UserProfile profile = profileService.findByUserLogin(user, lang);
        int sum = 0;
        for (CouponNew coupon : profile.getBoughtCoupons()) {
            sum += coupon.getCount();
        }
        for (CouponNew coupon : profile.getDoneCoupons()) {
            sum += coupon.getCount();
        }
        return sum;
    }

    @Override
    public void checkPermission(UserLogin user, String lang) {
        if (!getUserRole(user).equals(UserRole.ROLE_USER))
            throw new AccessErrorException(lang);
    }

    @Override
    public void saveUserPhoto(IdToken idToken, String lang) {
        UserLogin user = findByToken(idToken.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);

        Photo photo = photoService.getPhoto(idToken.getId(), lang);
        profile.setPhotos(Collections.singletonList(photo));
    }
}
