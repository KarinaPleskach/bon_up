package com.bsuir.diploma.bonup.service.additional.impl;

import com.bsuir.diploma.bonup.dao.additional.CountryRepository;
import com.bsuir.diploma.bonup.dto.converter.additional.CountryToIdStringDtoConverter;
import com.bsuir.diploma.bonup.dto.model.IdStringDto;
import com.bsuir.diploma.bonup.exception.additional.NoSuchCountryException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.additional.Country;
import com.bsuir.diploma.bonup.service.additional.CountryService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private TranslationService translationService;

    @Autowired
    private CountryToIdStringDtoConverter countryToIdStringDtoConverter;

    @Override
    public List<IdStringDto> getAllNamesWithId(String lang) {
        return countryRepository.findAll()
                        .stream()
                        .map(country -> countryToIdStringDtoConverter.convert(country))
                        .map(idStringDto -> new IdStringDto(idStringDto.getId(), translationService.getMessage(idStringDto.getName(), lang)))
                        .collect(Collectors.toList());
    }

    @Override
    public Country get(Long id, String lang) {
        if (Objects.isNull(id)) {
            throw new NullValidationException(lang);
        }

        return countryRepository.findById(id)
                .orElseThrow(() -> new NoSuchCountryException(lang));
    }

}
