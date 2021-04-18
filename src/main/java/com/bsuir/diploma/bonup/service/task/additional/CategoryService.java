package com.bsuir.diploma.bonup.service.task.additional;

import com.bsuir.diploma.bonup.dto.model.Id;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import java.util.List;
import java.util.Map;

public interface CategoryService {
    Map<Long, String> getAll(String lang);
    Map<Long, String> getAll(String lang, Id id);

    Category getSubCategoryById(Long id, String lang);

    Category getById(Long id, String lang);

    List<Category> getSubCategoriesByCategoryId(Long id, String lang);
}
