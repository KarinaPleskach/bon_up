package com.bsuir.diploma.bonup.service.additional.impl;

import com.bsuir.diploma.bonup.dao.additional.CityRepository;
import com.bsuir.diploma.bonup.dto.converter.additional.CityToIdStringDtoConverter;
import com.bsuir.diploma.bonup.dto.model.IdStringDto;
import com.bsuir.diploma.bonup.exception.additional.NoSuchCityException;
import com.bsuir.diploma.bonup.model.additional.City;
import com.bsuir.diploma.bonup.service.additional.CityService;
import com.bsuir.diploma.bonup.service.additional.CountryService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private TranslationService translationService;
    @Autowired
    private CountryService countryService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityToIdStringDtoConverter cityToIdStringDtoConverter;

    @Override
    public List<IdStringDto> getAllNamesWithId(Long countryId, String lang) {
        return cityRepository.findByCountry(countryService.get(countryId, lang))
                .stream()
                .map(city -> cityToIdStringDtoConverter.convert(city))
                .map(idStringDto -> new IdStringDto(idStringDto.getId(), translationService.getMessage(idStringDto.getName(), lang)))
                .collect(Collectors.toList());
    }

    @Override
    public City get(Long id, String lang) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new NoSuchCityException(lang));
    }

}
