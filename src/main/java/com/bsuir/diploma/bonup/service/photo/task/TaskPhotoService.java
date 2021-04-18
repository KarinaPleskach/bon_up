package com.bsuir.diploma.bonup.service.photo.task;

import com.bsuir.diploma.bonup.dto.model.photo.IdPhotoDto;

public interface TaskPhotoService {
    void saveTaskPhoto(IdPhotoDto idPhotoDto, String lang);

    void deleteTaskPhoto(IdPhotoDto idPhotoDto, String lang);
}
