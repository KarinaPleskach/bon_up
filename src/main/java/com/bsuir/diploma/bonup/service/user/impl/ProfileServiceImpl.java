package com.bsuir.diploma.bonup.service.user.impl;

import com.bsuir.diploma.bonup.dao.user.UserProfileRepository;
import com.bsuir.diploma.bonup.exception.user.auth.NoSuchUserException;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserProfile;
import com.bsuir.diploma.bonup.service.user.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfile findByUserLogin(UserLogin userLogin, String lang) {
        return userProfileRepository.findByUserLogin(userLogin)
                .orElseThrow(() -> new NoSuchUserException(lang));
    }

}
