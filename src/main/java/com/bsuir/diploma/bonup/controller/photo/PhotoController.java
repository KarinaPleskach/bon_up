package com.bsuir.diploma.bonup.controller.photo;

import com.bsuir.diploma.bonup.dto.model.IdStringDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.service.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("{lang}/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping
    public ResponseEntity<ResponseWithMessage> upload(@PathVariable("lang") String lang, @RequestParam("file") MultipartFile multipartFile) {
        String message = photoService.savePhoto(multipartFile, lang).toString();
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("lang") String lang, @PathVariable("id") Long id) {
        Photo photo = photoService.getPhoto(id, lang);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getName() + "\"")
                .contentType(MediaType.valueOf(photo.getContentType()))
                .body(photo.getData());
    }

}
