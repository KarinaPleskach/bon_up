package com.bsuir.diploma.bonup.dto.converter.additional;

import com.bsuir.diploma.bonup.dto.model.IdStringDto;
import com.bsuir.diploma.bonup.model.additional.Country;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryToIdStringDtoConverter implements Converter<Country, IdStringDto> {

    @Override
    public IdStringDto convert(Country country) {
        return new IdStringDto(country.getId(), country.getLanguageKey());
    }

}