package com.bsuir.diploma.bonup.dto.response.task;

import com.bsuir.diploma.bonup.dto.model.task.coupon.PublicCouponDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseWithCoupons {
    private Boolean isSuccess;
    private String message;
    private List<PublicCouponDto> coupons;
}
