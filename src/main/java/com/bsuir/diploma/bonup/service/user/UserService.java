package com.bsuir.diploma.bonup.service.user;

import com.bsuir.diploma.bonup.dto.converter.user.UserInfoDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserRole;

public interface UserService {
    UserLogin findByToken(String token, String lang);
    UserLogin findByLogin(String login, String lang);
    UserRole getUserRole(TokenDto tokenUser, String lang);
    UserRole getUserRole(String token, String lang);
    UserRole getUserRole(UserLogin user);

    UserInfoDto userInfo(TokenDto tokenUser, String lang);

    int getAllBalls(UserLogin user, String lang);

    int spentBalls(UserLogin user, String lang);

    void checkPermission(UserLogin user, String lang);
}
