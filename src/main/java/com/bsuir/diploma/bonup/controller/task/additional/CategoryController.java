package com.bsuir.diploma.bonup.controller.task.additional;

import com.bsuir.diploma.bonup.dto.model.Id;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMap;
import com.bsuir.diploma.bonup.service.task.additional.CategoryService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TranslationService translationService;

    @PostMapping("/{lang}/categories")
    @ResponseBody
    public ResponseEntity<ResponseWithMap> getCategories(@PathVariable("lang") String lang) {
        Map<Long, String> map = categoryService.getAll(lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMap(true, message, map), HttpStatus.OK);
    }

    @PostMapping("/{lang}/subCategories")
    @ResponseBody
    public ResponseEntity<ResponseWithMap> getSubCategories(@PathVariable("lang") String lang, @RequestBody Id id) {
        Map<Long, String> map = categoryService.getAll(lang, id);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMap(true, message, map), HttpStatus.OK);
    }

}
