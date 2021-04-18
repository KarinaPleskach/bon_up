package com.bsuir.diploma.bonup.dao.organization;

import com.bsuir.diploma.bonup.model.organization.Employee;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserLogin(UserLogin userLogin);
    Optional<Employee> findByUserLoginAndOrganization(UserLogin userLogin, Organization organization);
    List<Employee> findByOrganization(Organization organization);
}
