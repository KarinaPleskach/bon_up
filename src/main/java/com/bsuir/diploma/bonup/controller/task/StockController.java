package com.bsuir.diploma.bonup.controller.task;

import com.bsuir.diploma.bonup.dto.model.IdToken;
import com.bsuir.diploma.bonup.dto.model.organization.TokenNameOrganization;
import com.bsuir.diploma.bonup.dto.model.photo.IdPhotoDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.PageStockByCategoryDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.PublicStockDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.SetNameAndDescriptionDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.StockDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.dto.response.task.ResponseWithStock;
import com.bsuir.diploma.bonup.dto.response.task.ResponseWithStocks;
import com.bsuir.diploma.bonup.service.photo.task.StockPhotoService;
import com.bsuir.diploma.bonup.service.task.StockService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StockController {

    @Autowired
    private StockService stockService;
    @Autowired
    private TranslationService translationService;
    @Autowired
    private StockPhotoService stockPhotoService;

    @PutMapping("/{lang}/stock")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> createStock(@PathVariable("lang") String lang, @RequestBody StockDto stockDto) {
        String message = String.valueOf(stockService.create(stockDto, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PatchMapping("/{lang}/setStockNameAndDescription")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> setStockNameAndDescription(@PathVariable("lang") String lang, @RequestBody SetNameAndDescriptionDto setNameAndDescriptionDto) {
        stockService.setNameAndDescription(setNameAndDescriptionDto, lang);
        String message = translationService.getMessage("message.setted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PutMapping("/{lang}/stockPhoto")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> saveStockPhoto(@PathVariable("lang") String lang, @RequestBody IdPhotoDto idPhotoDto) {
        stockPhotoService.saveStockPhoto(idPhotoDto, lang);
        String message = translationService.getMessage("message.setted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @DeleteMapping("/{lang}/stockPhoto")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> deleteStockPhoto(@PathVariable("lang") String lang, @RequestBody IdPhotoDto idPhotoDto) {
        stockPhotoService.deleteStockPhoto(idPhotoDto, lang);
        String message = translationService.getMessage("message.deleted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfActiveStocks")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> getNumberOfActiveStocks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        String message = String.valueOf(stockService.getNumberOfActiveStocks(tokenNameOrganization, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfUnactiveStocks")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> getNumberOfUnactiveStocks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        String message = String.valueOf(stockService.getNumberOfUnactiveStocks(tokenNameOrganization, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/stock")
    @ResponseBody
    public ResponseEntity<ResponseWithStock> getStock(@PathVariable("lang") String lang, @RequestBody IdToken id) {
        PublicStockDto stock = stockService.getStock(id, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithStock(true, message, stock), HttpStatus.OK);
    }

    @PostMapping("/{lang}/activeStocks")
    @ResponseBody
    public ResponseEntity<ResponseWithStocks> getActiveStocks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        List<PublicStockDto> stocks = stockService.getActiveStocks(tokenNameOrganization, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithStocks(true, message, stocks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/unactiveStocks")
    @ResponseBody
    public ResponseEntity<ResponseWithStocks> getUnactiveStocks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        List<PublicStockDto> stocks = stockService.getUnactiveStocks(tokenNameOrganization, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithStocks(true, message, stocks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/stocks")
    @ResponseBody
    public ResponseEntity<ResponseWithStocks> getStocks(@PathVariable("lang") String lang, @RequestBody PageStockByCategoryDto pageStockByCategoryDto) {
        List<PublicStockDto> stocks = stockService.getStocks(pageStockByCategoryDto, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithStocks(true, message, stocks), HttpStatus.OK);
    }

}
