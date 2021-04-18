package com.bsuir.diploma.bonup.controller.task.saved;

import com.bsuir.diploma.bonup.dto.model.IdToken;
import com.bsuir.diploma.bonup.dto.model.task.stock.PublicStockDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.dto.response.task.ResponseWithStocks;
import com.bsuir.diploma.bonup.service.task.StockService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SavedStocksController {

    @Autowired
    private StockService stockService;
    @Autowired
    private TranslationService translationService;

    @PatchMapping("/{lang}/saveOrUnsaveStock")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> saveOrUnsave(@PathVariable("lang") String lang, @RequestBody IdToken idToken) {
        stockService.saveOrUnsave(idToken, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfSavedStocks")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> getNumberOfSavegStocks(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        String message = String.valueOf(stockService.getNumberOfSavedStocks(tokenUser, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/savedStocks")
    @ResponseBody
    public ResponseEntity<ResponseWithStocks> getSavedStocks(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        List<PublicStockDto> stocks = stockService.getSavedStocks(tokenUser, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithStocks(true, message, stocks), HttpStatus.OK);
    }

}
