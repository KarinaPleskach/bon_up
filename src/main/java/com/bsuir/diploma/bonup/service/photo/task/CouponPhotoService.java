package com.bsuir.diploma.bonup.service.photo.task;

import com.bsuir.diploma.bonup.dto.model.photo.IdPhotoDto;

public interface CouponPhotoService {
    void saveCouponPhoto(IdPhotoDto idPhotoDto, String lang);

    void deleteCouponPhoto(IdPhotoDto idPhotoDto, String lang);
}
