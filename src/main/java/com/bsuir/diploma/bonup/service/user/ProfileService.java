package com.bsuir.diploma.bonup.service.user;

import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserProfile;

public interface ProfileService {
    UserProfile findByUserLogin(UserLogin userLogin, String lang);
}
