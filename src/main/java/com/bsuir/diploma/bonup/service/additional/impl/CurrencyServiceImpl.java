package com.bsuir.diploma.bonup.service.additional.impl;

import com.bsuir.diploma.bonup.dao.additional.CurrencyRepository;
import com.bsuir.diploma.bonup.dto.converter.additional.CurrencyDtoToCurrencyConverter;
import com.bsuir.diploma.bonup.dto.model.additional.CurrencyDto;
import com.bsuir.diploma.bonup.exception.additional.CurrencyAlreadyExistException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.additional.Currency;
import com.bsuir.diploma.bonup.service.additional.CurrencyService;
import com.bsuir.diploma.bonup.service.security.PermissionService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyDtoToCurrencyConverter currencyDtoToCurrencyConverter;

    @Override
    public void addNewCurrency(CurrencyDto currencyDto, String lang) {
        validateCurrencyDto(currencyDto, lang);
        permissionService.checkGlobalAdminPermission(currencyDto.getToken(), lang);

        Currency currency = currencyDtoToCurrencyConverter.convert(currencyDto);
        currencyRepository.save(currency);
    }

    private void validateCurrencyDto(CurrencyDto currencyDto, String lang) {
        if (Objects.isNull(currencyDto) || Objects.isNull(currencyDto.getReduction()) || Objects.isNull(currencyDto.getLanguageKey()) || Objects.isNull(currencyDto.getSymbol())) {
            throw new NullValidationException(lang);
        }
        if (currencyRepository.findByLanguageKey(currencyDto.getLanguageKey()).isPresent()
                || currencyRepository.findByReduction(currencyDto.getReduction()).isPresent()) {
            throw new CurrencyAlreadyExistException(lang);
        }
    }

}
