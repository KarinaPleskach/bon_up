package com.bsuir.diploma.bonup.controller.task.saved;

import com.bsuir.diploma.bonup.dto.model.IdToken;
import com.bsuir.diploma.bonup.dto.model.task.coupon.PublicCouponDto;
import com.bsuir.diploma.bonup.dto.model.task.employee.EmployeeResolveUserDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.PublicStockDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.dto.response.task.ResponseWithCoupons;
import com.bsuir.diploma.bonup.dto.response.task.ResponseWithStocks;
import com.bsuir.diploma.bonup.service.task.CouponService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SavedCouponController {

    @Autowired
    private CouponService couponService;
    @Autowired
    private TranslationService translationService;

    @PatchMapping("/{lang}/saveOrUnsaveCoupon")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> saveOrUnsave(@PathVariable("lang") String lang, @RequestBody IdToken idToken) {
        couponService.saveOrUnsave(idToken, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfSavedCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> getNumberOfSavegStocks(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        String message = String.valueOf(couponService.getNumberOfSavedCoupons(tokenUser, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/savedCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithCoupons> getSavedStocks(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        List<PublicCouponDto> stocks = couponService.getSavedCoupons(tokenUser, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithCoupons(true, message, stocks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/buyCoupon")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> resolveTask(@PathVariable("lang") String lang, @RequestBody IdToken idToken) {
        couponService.buyCoupon(idToken, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/boughtCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithCoupons> doneTasks(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        List<PublicCouponDto> tasks = couponService.getBoughtCoupons(tokenUser, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithCoupons(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfBoughtCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfDoneTasks(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        String message = String.valueOf(couponService.getNumberOfBoughtCoupons(tokenUser, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/canActivateCoupon")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> canActivateCoupon(@PathVariable("lang") String lang, @RequestBody IdToken idToken) {
        couponService.canActivateCoupon(idToken, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/activateCoupon")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> activateCoupon(@PathVariable("lang") String lang, @RequestBody EmployeeResolveUserDto employeeResolveUserDto) {
        couponService.activateCoupon(employeeResolveUserDto, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/activateCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithCoupons> activateCoupons(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        List<PublicCouponDto> tasks = couponService.getActivateCoupons(tokenUser, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithCoupons(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfActivateCoupons")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfActivateCoupons(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        String message = String.valueOf(couponService.getNumberOfActivateCoupons(tokenUser, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

}
