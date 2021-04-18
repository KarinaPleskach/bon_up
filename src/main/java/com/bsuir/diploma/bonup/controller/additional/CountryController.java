package com.bsuir.diploma.bonup.controller.additional;

import com.bsuir.diploma.bonup.dto.model.IdStringDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithIdStringDtos;
import com.bsuir.diploma.bonup.service.additional.CountryService;
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
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private TranslationService translationService;

    @PostMapping("/{lang}/country/names")
    @ResponseBody
    public ResponseEntity<ResponseWithIdStringDtos> countries(@PathVariable("lang") String lang) {
        List<IdStringDto> list = countryService.getAllNamesWithId(lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithIdStringDtos(true, message, list), HttpStatus.OK);
    }

}
