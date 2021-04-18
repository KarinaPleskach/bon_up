package com.bsuir.diploma.bonup.service.additional;

import com.bsuir.diploma.bonup.dto.model.additional.CurrencyDto;

public interface CurrencyService {
    void addNewCurrency(CurrencyDto currencyDto, String lang);
}
