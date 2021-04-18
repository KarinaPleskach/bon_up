package com.bsuir.diploma.bonup.dao.task.additional;

import com.bsuir.diploma.bonup.model.task.additional.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCategory(Category category);
}
