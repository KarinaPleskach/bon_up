package com.bsuir.diploma.bonup.service.task;

import com.bsuir.diploma.bonup.dto.model.IdToken;
import com.bsuir.diploma.bonup.dto.model.organization.TokenNameOrganization;
import com.bsuir.diploma.bonup.dto.model.task.stock.PageStockByCategoryDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.PublicStockDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.SetNameAndDescriptionDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.StockDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import java.util.List;

public interface StockService {
    long create(StockDto stockDto, String lang);
    void setNameAndDescription(SetNameAndDescriptionDto setNameAndDescriptionDto, String lang);
    int getNumberOfActiveStocks(TokenNameOrganization tokenNameOrganization, String lang);
    int getNumberOfUnactiveStocks(TokenNameOrganization tokenNameOrganization, String lang);
    PublicStockDto getStock(IdToken idToken, String lang);
    List<PublicStockDto> getActiveStocks(TokenNameOrganization tokenNameOrganization, String lang);
    List<PublicStockDto> getUnactiveStocks(TokenNameOrganization tokenNameOrganization, String lang);
    void saveOrUnsave(IdToken idToken, String lang);
    int getNumberOfSavedStocks(TokenDto tokenUser, String lang);
    List<PublicStockDto> getSavedStocks(TokenDto tokenUser, String lang);
    List<PublicStockDto> getStocks(PageStockByCategoryDto pageStockByCategoryDto, String lang);
}
