package com.bsuir.diploma.bonup.controller.additional;

import com.bsuir.diploma.bonup.dto.model.additional.CurrencyDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.service.additional.CurrencyService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private TranslationService translationService;

    @PutMapping("/{lang}/currency")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> addCurrency(@PathVariable("lang") String lang, @RequestBody CurrencyDto currencyDto) {
        currencyService.addNewCurrency(currencyDto, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

}
