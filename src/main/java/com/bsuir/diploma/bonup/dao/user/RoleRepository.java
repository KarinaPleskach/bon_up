package com.bsuir.diploma.bonup.dao.user;

import com.bsuir.diploma.bonup.model.user.Role;
import com.bsuir.diploma.bonup.model.user.UserRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByDescription(UserRole userRole);
}
