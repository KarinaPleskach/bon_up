package com.bsuir.diploma.bonup.controller.task.additional;

import com.bsuir.diploma.bonup.dto.model.task.additional.PublicTypeDto;
import com.bsuir.diploma.bonup.dto.response.task.additional.ResponseWithTypes;
import com.bsuir.diploma.bonup.service.task.additional.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private TranslationService translationService;

    @PostMapping("/{lang}/types")
    @ResponseBody
    public ResponseEntity<ResponseWithTypes> getAll(@PathVariable("lang") String lang) {
        List<PublicTypeDto> types = typeService.getAll(lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithTypes(true, message, types), HttpStatus.OK);
    }


}
