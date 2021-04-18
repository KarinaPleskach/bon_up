package com.bsuir.diploma.bonup.service.additional;

import com.bsuir.diploma.bonup.dto.model.IdStringDto;
import com.bsuir.diploma.bonup.model.additional.City;
import java.util.List;

public interface CityService {
    List<IdStringDto> getAllNamesWithId(Long countryId, String lang);
    City get(Long id, String lang);
}
