package com.bsuir.diploma.bonup.service.task.additional.impl;

import com.bsuir.diploma.bonup.dao.task.additional.CategoryRepository;
import com.bsuir.diploma.bonup.dto.model.Id;
import com.bsuir.diploma.bonup.exception.task.additional.NoSuchCategoryException;
import com.bsuir.diploma.bonup.exception.task.additional.NotSubCategoryException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import com.bsuir.diploma.bonup.service.task.additional.CategoryService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TranslationService translationService;

    @Override
    public Map<Long, String> getAll(String lang) {
        return categoryRepository.findAll().stream()
                .filter(category -> Objects.isNull(category.getCategory()))
                .collect( Collectors.toMap(Category::getId,
                        category -> translationService.getMessage(category.getKey(), lang)));
    }

    @Override
    public Map<Long, String> getAll(String lang, Id id) {
        validateId(id, lang);
        return categoryRepository.findAll().stream()
                .filter(category -> Objects.nonNull(category.getCategory()))
                .filter(category -> category.getCategory().getId().equals(id.getId()))
                .collect( Collectors.toMap(Category::getId,
                        category -> translationService.getMessage(category.getKey(), lang)));
    }

    @Override
    public Category getSubCategoryById(Long id, String lang) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchCategoryException(lang));
        if (Objects.isNull(category.getCategory()))
            throw new NotSubCategoryException(lang);
        return category;
    }

    private void validateId(Id id, String lang) {
        if (Objects.isNull(id) || Objects.isNull(id.getId())) {
            throw new NullValidationException(lang);
        }
    }

    @Override
    public Category getById(Long id, String lang) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchCategoryException(lang));
    }

    @Override
    public List<Category> getSubCategoriesByCategoryId(Long id, String lang) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchCategoryException(lang));
        return categoryRepository.findAllByCategory(category);
    }

}
