package com.bsuir.diploma.bonup.dto.response.task;

import com.bsuir.diploma.bonup.dto.model.task.coupon.PublicCouponDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ResponseWithCoupon {
    private Boolean isSuccess;
    private String message;
    private PublicCouponDto coupon;
}
