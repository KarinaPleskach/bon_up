package com.bsuir.diploma.bonup.dao.organization;

import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.organization.OrganizationNew;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationNewDao extends JpaRepository<OrganizationNew, Long> {
    Optional<OrganizationNew> findByTitle(String title);
    List<OrganizationNew> findByUserLogin(UserLogin userLogin);
}

