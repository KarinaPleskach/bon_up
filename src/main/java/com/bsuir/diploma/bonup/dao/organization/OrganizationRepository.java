package com.bsuir.diploma.bonup.dao.organization;

import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findByName(String name);
    List<Organization> findByUserLogin(UserLogin userLogin);
    Optional<Organization> findByNameAndUserLogin(String name, UserLogin userLogin);
}
