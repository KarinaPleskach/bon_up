package com.bsuir.diploma.bonup.service.photo.impl;

import com.bsuir.diploma.bonup.dao.photo.PhotoRepository;
import com.bsuir.diploma.bonup.exception.photo.NoSuchPhotoException;
import com.bsuir.diploma.bonup.exception.validation.DataNotFoundException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.service.photo.PhotoService;
import java.io.IOException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Long savePhoto(MultipartFile multipartFile, String lang) {
        validateFile(multipartFile, lang);

        Photo photo = new Photo();
        photo.setName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        photo.setContentType(multipartFile.getContentType());
        try {
            photo.setData(multipartFile.getBytes());
        } catch (IOException e) {
            throw new DataNotFoundException(lang);
        }
        photo.setSize(multipartFile.getSize());

        return photoRepository.save(photo).getId();
    }

    @Override
    public Photo getPhoto(Long id, String lang) {
        if (Objects.isNull(id)) {
            throw new NullValidationException(lang);
        }
        return photoRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(lang));
    }

    @Override
    public void delete(Photo photo, String lang) {
        if (Objects.isNull(photo)) {
            throw new NoSuchPhotoException(lang);
        }
        photoRepository.delete(photo);
    }

    private void validateFile(MultipartFile multipartFile, String lang) {
        if (Objects.isNull(multipartFile)) {
            throw new NullValidationException(lang);
        }
    }

}
