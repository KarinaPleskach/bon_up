package com.bsuir.diploma.bonup.service.security.impl;

import com.bsuir.diploma.bonup.exception.user.auth.AccessErrorException;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserRole;
import com.bsuir.diploma.bonup.service.security.PermissionService;
import com.bsuir.diploma.bonup.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserService userService;

    @Override
    public void checkGlobalAdminPermission(UserLogin userLogin, String lang) {
        if (!userService.getUserRole(userLogin).equals(UserRole.ROLE_GLOBAL_ADMIN)) {
            throw new AccessErrorException(lang);
        }
    }

    @Override
    public void checkGlobalAdminPermission(String token, String lang) {
        if (!userService.getUserRole(token, lang).equals(UserRole.ROLE_GLOBAL_ADMIN)) {
            throw new AccessErrorException(lang);
        }
    }

}
