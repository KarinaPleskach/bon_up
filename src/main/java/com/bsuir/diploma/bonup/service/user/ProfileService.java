package com.bsuir.diploma.bonup.service.user;

import com.bsuir.diploma.bonup.dto.model.TokenIdsDro;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserProfile;

public interface ProfileService {
    UserProfile findByUserLogin(UserLogin userLogin, String lang);

    void setCategories(TokenIdsDro tokenIdsDro, String lang);
}
