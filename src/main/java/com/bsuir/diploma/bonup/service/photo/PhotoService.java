package com.bsuir.diploma.bonup.service.photo;

import com.bsuir.diploma.bonup.model.photo.Photo;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    Long savePhoto(MultipartFile multipartFile, String lang);
    Photo getPhoto(Long id, String lang);

    void delete(Photo photo, String lang);
}
