package com.bsuir.diploma.bonup.service.task.additional.impl;

import com.bsuir.diploma.bonup.dao.task.additional.TypeRepository;
import com.bsuir.diploma.bonup.dto.converter.task.additional.TypeToPublicTypeDtoConverter;
import com.bsuir.diploma.bonup.dto.model.task.additional.PublicTypeDto;
import com.bsuir.diploma.bonup.exception.task.additional.NoSuchTypeException;
import com.bsuir.diploma.bonup.model.task.additional.Type;
import com.bsuir.diploma.bonup.service.task.additional.TypeService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private TranslationService translationService;

    @Autowired
    private TypeToPublicTypeDtoConverter typeToPublicTypeDtoConverter;

    @Override
    public List<PublicTypeDto> getAll(String lang) {
        return typeRepository.findAll().stream()
                .map(type -> typeToPublicTypeDtoConverter.convert(type))
                .peek(type -> {
                    type.setDescription(translationService.getMessage(type.getDescription(), lang));
                })
                .collect(Collectors.toList());
    }

    @Override
    public Type getById(Long id, String lang) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new NoSuchTypeException(lang));
    }

}
