package com.bsuir.diploma.bonup.dto.converter.additional;

import com.bsuir.diploma.bonup.dto.model.IdStringDto;
import com.bsuir.diploma.bonup.model.additional.City;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CityToIdStringDtoConverter implements Converter<City, IdStringDto> {

    @Override
    public IdStringDto convert(City city) {
        return new IdStringDto(city.getId(), city.getLanguageKey());
    }

}
