package com.bsuir.diploma.bonup.controller.additional;

import com.bsuir.diploma.bonup.dto.model.IdStringDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithIdStringDtos;
import com.bsuir.diploma.bonup.service.additional.CityService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private TranslationService translationService;


    @PostMapping("/{lang}/city/names/{id}")
    @ResponseBody
    public ResponseEntity<ResponseWithIdStringDtos> cities(@PathVariable("lang") String lang, @PathVariable("id") Long id) {
        List<IdStringDto> list = cityService.getAllNamesWithId(id, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithIdStringDtos(true, message, list), HttpStatus.OK);
    }

}
