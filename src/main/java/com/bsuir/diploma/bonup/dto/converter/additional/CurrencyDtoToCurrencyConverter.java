package com.bsuir.diploma.bonup.dto.converter.additional;

import com.bsuir.diploma.bonup.dto.model.additional.CurrencyDto;
import com.bsuir.diploma.bonup.model.additional.Currency;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyDtoToCurrencyConverter implements Converter<CurrencyDto, Currency> {

    @Override
    public Currency convert(CurrencyDto currencyDto) {
        return Currency.builder()
                .reduction(currencyDto.getReduction())
                .languageKey(currencyDto.getLanguageKey())
                .symbol(currencyDto.getSymbol())
                .build();
    }

}
