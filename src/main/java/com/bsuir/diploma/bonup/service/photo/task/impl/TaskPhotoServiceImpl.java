package com.bsuir.diploma.bonup.service.photo.task.impl;

import com.bsuir.diploma.bonup.dao.task.TaskRepository;
import com.bsuir.diploma.bonup.dto.model.photo.IdPhotoDto;
import com.bsuir.diploma.bonup.exception.photo.NoSuchPhotoException;
import com.bsuir.diploma.bonup.exception.photo.PhotoAlreadyExistException;
import com.bsuir.diploma.bonup.exception.task.NoSuchStockException;
import com.bsuir.diploma.bonup.exception.user.auth.AccessErrorException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.Task;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserRole;
import com.bsuir.diploma.bonup.service.photo.PhotoService;
import com.bsuir.diploma.bonup.service.photo.task.TaskPhotoService;
import com.bsuir.diploma.bonup.service.user.UserService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskPhotoServiceImpl implements TaskPhotoService {

    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void saveTaskPhoto(IdPhotoDto idPhotoDto, String lang) {
        Task task = validateIdPhotoDto(idPhotoDto, lang);
        if (task.getPhotos().stream().anyMatch(photo -> photo.getId().equals(idPhotoDto.getPhotoId()))) {
            throw new PhotoAlreadyExistException(lang);
        }

        Photo photo = photoService.getPhoto(idPhotoDto.getPhotoId(), lang);
        task.getPhotos().add(photo);
    }

    @Override
    public void deleteTaskPhoto(IdPhotoDto idPhotoDto, String lang) {
        Task task = validateIdPhotoDto(idPhotoDto, lang);
        if (task.getPhotos().stream().noneMatch(photo -> photo.getId().equals(idPhotoDto.getPhotoId()))) {
            throw new NoSuchPhotoException(lang);
        }
        Photo photo = photoService.getPhoto(idPhotoDto.getPhotoId(), lang);
        task.getPhotos().remove(photo);
        photoService.delete(photo, lang);
    }

    private Task validateIdPhotoDto(IdPhotoDto idPhotoDto, String lang) {
        if (Objects.isNull(idPhotoDto) || Objects.isNull(idPhotoDto.getToken()) || Objects.isNull(idPhotoDto.getId())
                || Objects.isNull(idPhotoDto.getPhotoId())) {
            throw new NullValidationException(lang);
        }
        UserLogin user = userService.findByToken(idPhotoDto.getToken(), lang);
        if (!userService.getUserRole(user).equals(UserRole.ROLE_ORGANIZATION_ADMIN))
            throw new AccessErrorException(lang);
        Task task = taskRepository.findById(idPhotoDto.getId())
                .orElseThrow(() -> new NoSuchStockException(lang));
        if (!task.getOrganization().getUserLogin().equals(user)) {
            throw new AccessErrorException(lang);
        }
        return task;
    }

}
