package com.bsuir.diploma.bonup.service.task.additional;

import com.bsuir.diploma.bonup.dto.model.task.additional.PublicTypeDto;
import com.bsuir.diploma.bonup.model.task.additional.Type;
import java.util.List;

public interface TypeService {
    List<PublicTypeDto> getAll(String lang);

    Type getById(Long id, String lang);
}
