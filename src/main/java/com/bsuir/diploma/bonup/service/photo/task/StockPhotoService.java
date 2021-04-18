package com.bsuir.diploma.bonup.service.photo.task;

import com.bsuir.diploma.bonup.dto.model.photo.IdPhotoDto;

public interface StockPhotoService {
    void saveStockPhoto(IdPhotoDto idPhotoDto, String lang);

    void deleteStockPhoto(IdPhotoDto idPhotoDto, String lang);
}
