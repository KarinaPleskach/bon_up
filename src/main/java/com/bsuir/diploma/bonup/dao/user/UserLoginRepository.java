package com.bsuir.diploma.bonup.dao.user;

import com.bsuir.diploma.bonup.model.user.UserLogin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    Optional<UserLogin> findByLogin(String login);
    Optional<UserLogin> findByToken(String token);
}

