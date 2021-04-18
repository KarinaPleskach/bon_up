package com.bsuir.diploma.bonup.controller.task;

import com.bsuir.diploma.bonup.dto.model.IdToken;
import com.bsuir.diploma.bonup.dto.model.organization.TokenNameOrganization;
import com.bsuir.diploma.bonup.dto.model.photo.IdPhotoDto;
import com.bsuir.diploma.bonup.dto.model.task.coupon.CouponDto;
import com.bsuir.diploma.bonup.dto.model.task.coupon.PublicCouponDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.PageStockByCategoryDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.SetNameAndDescriptionDto;
import com.bsuir.diploma.bonup.dto.model.task.task.TaskDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.dto.response.task.ResponseWithCoupon;
import com.bsuir.diploma.bonup.dto.response.task.ResponseWithCoupons;
import com.bsuir.diploma.bonup.model.task.additional.Type;
import com.bsuir.diploma.bonup.service.photo.task.CouponPhotoService;
import com.bsuir.diploma.bonup.service.task.CouponService;
import com.bsuir.diploma.bonup.service.task.additional.TypeService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CouponController {

    @Autowired
    private CouponService couponService;
    @Autowired
    private TranslationService translationService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private CouponPhotoService couponPhotoService;


    @PutMapping("/{lang}/coupon")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> createTask(@PathVariable("lang") String lang, @RequestBody CouponDto taskDto) {
        String message = String.valueOf(couponService.create(taskDto, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PatchMapping("/{lang}/setCouponNameAndDescription")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> setTaskNameAndDescription(@PathVariable("lang") String lang, @RequestBody SetNameAndDescriptionDto setNameAndDescriptionDto) {
        couponService.setNameAndDescription(setNameAndDescriptionDto, lang);
        String message = translationService.getMessage("message.setted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PutMapping("/{lang}/couponPhoto")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> saveTaskPhoto(@PathVariable("lang") String lang, @RequestBody IdPhotoDto idPhotoDto) {
        couponPhotoService.saveCouponPhoto(idPhotoDto, lang);
        String message = translationService.getMessage("message.setted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @DeleteMapping("/{lang}/couponPhoto")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> deleteTaskPhoto(@PathVariable("lang") String lang, @RequestBody IdPhotoDto idPhotoDto) {
        couponPhotoService.deleteCouponPhoto(idPhotoDto, lang);
        String message = translationService.getMessage("message.deleted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfActiveHeavyCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfActiveHeavyTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        Type type = typeService.getById(1L, lang);
        String message = String.valueOf(couponService.getNumberOfActiveCoupons(tokenNameOrganization, type, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfActiveMediumCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfActiveMediumTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        Type type = typeService.getById(2L, lang);
        String message = String.valueOf(couponService.getNumberOfActiveCoupons(tokenNameOrganization, type, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfActiveEasyCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfActiveEasyTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        Type type = typeService.getById(3L, lang);
        String message = String.valueOf(couponService.getNumberOfActiveCoupons(tokenNameOrganization, type, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfActiveCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfActiveTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        String message = String.valueOf(couponService.getNumberOfActiveCoupons(tokenNameOrganization, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfUnactiveCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfUnactiveTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        String message = String.valueOf(couponService.getNumberOfUnactiveCoupons(tokenNameOrganization, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/coupon")
    @ResponseBody
    public ResponseEntity<ResponseWithCoupon> getTask(@PathVariable("lang") String lang, @RequestBody IdToken id) {
        PublicCouponDto task = couponService.getCoupon(id, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithCoupon(true, message, task), HttpStatus.OK);
    }

    @PostMapping("/{lang}/activeCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithCoupons> activeTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        List<PublicCouponDto> tasks = couponService.getActiveCoupons(tokenNameOrganization, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithCoupons(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/unactiveCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithCoupons> unactiveTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        List<PublicCouponDto> tasks = couponService.getUnactiveCoupons(tokenNameOrganization, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithCoupons(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/activeEasyCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithCoupons> activeEasyTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        Type type = typeService.getById(3L, lang);
        List<PublicCouponDto> tasks = couponService.getActiveCoupons(tokenNameOrganization, type, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithCoupons(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/activeMediumCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithCoupons> activeMediumTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        Type type = typeService.getById(2L, lang);
        List<PublicCouponDto> tasks = couponService.getActiveCoupons(tokenNameOrganization, type, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithCoupons(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/activeHeavyCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithCoupons> activeHeavyTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        Type type = typeService.getById(1L, lang);
        List<PublicCouponDto> tasks = couponService.getActiveCoupons(tokenNameOrganization, type, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithCoupons(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/coupons")
    @ResponseBody
    public ResponseEntity<ResponseWithCoupons> getTasks(@PathVariable("lang") String lang, @RequestBody PageStockByCategoryDto pageStockByCategoryDto) {
        List<PublicCouponDto> tasks = couponService.getCoupons(pageStockByCategoryDto, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithCoupons(true, message, tasks), HttpStatus.OK);
    }

}
