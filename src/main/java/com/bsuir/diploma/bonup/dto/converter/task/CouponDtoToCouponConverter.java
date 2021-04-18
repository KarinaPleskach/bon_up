package com.bsuir.diploma.bonup.dto.converter.task;

import com.bsuir.diploma.bonup.dto.model.task.coupon.CouponDto;
import com.bsuir.diploma.bonup.model.task.Coupon;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CouponDtoToCouponConverter implements Converter<CouponDto, Coupon> {

    @Override
    public Coupon convert(CouponDto couponDto) {
        return Coupon.builder()
                .nameKey(couponDto.getName())
                .descriptionKey(couponDto.getDescription())
                .dateFrom(couponDto.getDateFrom())
                .dateTo(couponDto.getDateTo())
                .count(couponDto.getCount())
                .activity(couponDto.getActivity())
                .build();
    }
}
