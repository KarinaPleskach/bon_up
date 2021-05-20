package com.bsuir.diploma.bonup.service.user.impl;

import com.bsuir.diploma.bonup.dao.user.UserProfileRepository;
import com.bsuir.diploma.bonup.dto.model.TokenIdsDro;
import com.bsuir.diploma.bonup.exception.user.auth.NoSuchUserException;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserProfile;
import com.bsuir.diploma.bonup.service.task.additional.CategoryService;
import com.bsuir.diploma.bonup.service.user.ProfileService;
import com.bsuir.diploma.bonup.service.user.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public UserProfile findByUserLogin(UserLogin userLogin, String lang) {
        return userProfileRepository.findByUserLogin(userLogin)
                .orElseThrow(() -> new NoSuchUserException(lang));
    }

    @Override
    public void setCategories(TokenIdsDro tokenIdsDro, String lang) {
        UserLogin userLogin = userService.findByToken(tokenIdsDro.getToken(), lang);
        UserProfile userProfile = findByUserLogin(userLogin, lang);
        List<Category> categories = new ArrayList<>();

        for (Long id : tokenIdsDro.getIds()) {
            categories.add(categoryService.getById(id, lang));
        }

        userProfile.setCategories(categories);
    }

}
