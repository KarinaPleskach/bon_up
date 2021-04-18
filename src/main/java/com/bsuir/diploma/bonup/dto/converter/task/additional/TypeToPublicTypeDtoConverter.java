package com.bsuir.diploma.bonup.dto.converter.task.additional;

import com.bsuir.diploma.bonup.dto.model.task.additional.PublicTypeDto;
import com.bsuir.diploma.bonup.model.task.additional.Type;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeToPublicTypeDtoConverter implements Converter<Type, PublicTypeDto> {

    @Override
    public PublicTypeDto convert(Type type) {
        return PublicTypeDto.builder()
                .id(type.getId())
                .description(type.getKey())
                .pointsCount(type.getPointsCount())
                .costCount(type.getCostCount())
                .build();
    }
}
