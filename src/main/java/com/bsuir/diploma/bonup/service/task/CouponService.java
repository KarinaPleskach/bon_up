package com.bsuir.diploma.bonup.service.task;

import com.bsuir.diploma.bonup.dto.model.IdToken;
import com.bsuir.diploma.bonup.dto.model.organization.TokenNameOrganization;
import com.bsuir.diploma.bonup.dto.model.task.TaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.coupon.CouponDto;
import com.bsuir.diploma.bonup.dto.model.task.coupon.PublicCouponDto;
import com.bsuir.diploma.bonup.dto.model.task.employee.EmployeeResolveUserDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.PageStockByCategoryDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.SetNameAndDescriptionDto;
import com.bsuir.diploma.bonup.dto.model.task.task.PublicTaskDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.model.task.additional.Type;
import java.util.List;

public interface CouponService {
    long createTaskNew(TaskNewDto taskDto, String lang);

    long create(CouponDto taskDto, String lang);

    void setNameAndDescription(SetNameAndDescriptionDto setNameAndDescriptionDto, String lang);

    int getNumberOfActiveCoupons(TokenNameOrganization tokenNameOrganization, Type type, String lang);

    int getNumberOfActiveCoupons(TokenNameOrganization tokenNameOrganization, String lang);

    int getNumberOfUnactiveCoupons(TokenNameOrganization tokenNameOrganization, String lang);

    PublicCouponDto getCoupon(IdToken id, String lang);

    List<PublicCouponDto> getActiveCoupons(TokenNameOrganization tokenNameOrganization, String lang);

    List<PublicCouponDto> getActiveCoupons(TokenNameOrganization tokenNameOrganization, Type type, String lang);

    List<PublicCouponDto> getUnactiveCoupons(TokenNameOrganization tokenNameOrganization, String lang);

    List<PublicCouponDto> getCoupons(PageStockByCategoryDto pageStockByCategoryDto, String lang);

    void saveOrUnsave(IdToken idToken, String lang);

    int getNumberOfSavedCoupons(TokenDto tokenUser, String lang);

    List<PublicCouponDto> getSavedCoupons(TokenDto tokenUser, String lang);

    void buyCoupon(IdToken idToken, String lang);

    int getNumberOfBoughtCoupons(TokenDto tokenUser, String lang);

    List<PublicCouponDto> getBoughtCoupons(TokenDto tokenUser, String lang);

    void canActivateCoupon(IdToken idToken, String lang);

    void activateCoupon(EmployeeResolveUserDto employeeResolveUserDto, String lang);

    int getNumberOfActivateCoupons(TokenDto tokenUser, String lang);

    List<PublicCouponDto> getActivateCoupons(TokenDto tokenUser, String lang);
}
