package com.bsuir.diploma.bonup.service.user.impl;

import com.bsuir.diploma.bonup.dao.user.UserLoginRepository;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.exception.user.auth.NoSuchUserException;
import com.bsuir.diploma.bonup.exception.user.auth.RoleNotFoundException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.user.Role;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserRole;
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

}
