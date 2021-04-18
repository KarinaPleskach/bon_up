package com.bsuir.diploma.bonup.service.additional;

import com.bsuir.diploma.bonup.dto.model.IdStringDto;
import com.bsuir.diploma.bonup.model.additional.Country;
import java.util.List;

public interface CountryService {
    List<IdStringDto> getAllNamesWithId(String lang);

    Country get(Long id, String lang);
}
