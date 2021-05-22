package com.bsuir.diploma.bonup.controller.organization;

import com.bsuir.diploma.bonup.dto.model.organization.NewOrganizationWithPhoto;
import com.bsuir.diploma.bonup.dto.model.organization.OrganizationWithPhotoDto;
import com.bsuir.diploma.bonup.dto.model.organization.TokenNameOrganization;
import com.bsuir.diploma.bonup.dto.model.task.PublicTaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.TaskWithTriggerDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.OrganizationNewDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.dto.response.organization.ResponseWithNewOrganizations;
import com.bsuir.diploma.bonup.dto.response.organization.ResponseWithOrganizations;
import com.bsuir.diploma.bonup.dto.response.task.ResponseWithTasksAndCoupons;
import com.bsuir.diploma.bonup.service.organization.OrganizationNewService;
import com.bsuir.diploma.bonup.service.task.CouponService;
import com.bsuir.diploma.bonup.service.task.StockService;
import com.bsuir.diploma.bonup.service.task.TaskService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewOrganizationController {

    @Autowired
    private TranslationService translationService;
    @Autowired
    private OrganizationNewService organizationNewService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private StockService stockService;

    @PostMapping("/{lang}/newOrganization")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> addOrganization(@PathVariable("lang") String lang, @RequestBody OrganizationNewDto newOrganization) {
        organizationNewService.create(newOrganization, lang);
        String message = translationService.getMessage("message.organization.add", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/modifyOrganization")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> modifyOrganization(@PathVariable("lang") String lang, @RequestBody OrganizationNewDto newOrganization) {
        organizationNewService.modify(newOrganization, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/userOrganizations")
    @ResponseBody
    public List<NewOrganizationWithPhoto> getListOfOrganizations(@PathVariable("lang") String lang, @RequestBody TokenDto tokenDto) {
        List<NewOrganizationWithPhoto> list = organizationNewService.getOrganizations(tokenDto, lang);
//        String message = translationService.getMessage("message.success", lang);
        return list;
    }

    @PostMapping("/{lang}/organizationTasks")
    @ResponseBody
    public List<TaskWithTriggerDto> organizationTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenDto) {
        List<TaskWithTriggerDto> list = taskService.getAllForOrg(tokenDto, lang);
        return list;
    }

    @PostMapping("/{lang}/organizationCoupons")
    @ResponseBody
    public List<TaskWithTriggerDto> organizationCoupons(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenDto) {
        List<TaskWithTriggerDto> list = couponService.getAllForOrg(tokenDto, lang);
        return list;
    }

    @PostMapping("/{lang}/organizationStocks")
    @ResponseBody
    public List<PublicTaskNewDto> organizationStocks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenDto) {
        List<PublicTaskNewDto> list = stockService.getAllForOrg(tokenDto, lang);
        return list;
    }

    @PostMapping("/{lang}/organizationTasksAndCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithTasksAndCoupons> organizationTasksAndCoupons(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenDto) {
        List<TaskWithTriggerDto> tasks = taskService.getAllForOrg(tokenDto, lang);
        List<TaskWithTriggerDto> list = couponService.getAllForOrg(tokenDto, lang);
        return new ResponseEntity<>(new ResponseWithTasksAndCoupons(tasks, list), HttpStatus.OK);
    }
}
