package com.bsuir.diploma.bonup.dto.converter.task;

import com.bsuir.diploma.bonup.dto.model.task.coupon.PublicCouponDto;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.Coupon;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CouponToPublicCouponDtoConverter implements Converter<Coupon, PublicCouponDto> {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public PublicCouponDto convert(Coupon coupon) {
        List<Long> photos = null;
        if (Objects.nonNull(coupon.getPhotos())) {
            photos = coupon.getPhotos().stream().map(Photo::getId).collect(Collectors.toList());
        }
        return PublicCouponDto.builder()
                .id(coupon.getId())
                .name(coupon.getNameKey())
                .description(coupon.getDescriptionKey())
                .count(coupon.getCount())
                .dateFrom(format.format(coupon.getDateFrom().getTime()))
                .dateTo(format.format(coupon.getDateTo().getTime()))
                .organizationName(coupon.getOrganization().getName())
                .photos(photos)
                .subcategoryId(coupon.getCategory().getId())
                .subcategory(coupon.getCategory().getKey())
                .categoryId(coupon.getCategory().getCategory().getId())
                .category(coupon.getCategory().getCategory().getKey())
                .typeId(coupon.getType().getId())
                .type(coupon.getType().getKey())
                .pointsCount(coupon.getType().getPointsCount())
                .activity(coupon.getActivity())
                .beloved(null)
                .done(null)
                .bought(null)
                .build();
    }
}
