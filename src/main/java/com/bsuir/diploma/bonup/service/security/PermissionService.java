package com.bsuir.diploma.bonup.service.security;

import com.bsuir.diploma.bonup.model.user.UserLogin;

public interface PermissionService {
    void checkGlobalAdminPermission(UserLogin userLogin, String lang);

    void checkGlobalAdminPermission(String token, String lang);
}
