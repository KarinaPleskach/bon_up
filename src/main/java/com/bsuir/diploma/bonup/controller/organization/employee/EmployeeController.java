package com.bsuir.diploma.bonup.controller.organization.employee;

import com.bsuir.diploma.bonup.dto.model.organization.employee.NewEmployeeDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.service.organization.employee.EmployeeService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TranslationService translationService;

    @PutMapping("/{lang}/employee")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> registerOrganizationEmployee(@PathVariable("lang") String lang, @RequestBody NewEmployeeDto employeeDto) {
        employeeService.createEmployeeUser(employeeDto, lang);
        String message = translationService.getMessage("message.register.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

}
