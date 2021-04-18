package com.bsuir.diploma.bonup.service.organization.employee;

import com.bsuir.diploma.bonup.dto.model.organization.employee.NewEmployeeDto;
import com.bsuir.diploma.bonup.model.organization.Employee;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import java.util.List;

public interface EmployeeService {
    List<Employee> findByOrganization(Organization organization);

    Employee findByUserLogin(UserLogin userLogin, String lang);

    void createEmployeeUser(NewEmployeeDto employeeDto, String lang);
}
