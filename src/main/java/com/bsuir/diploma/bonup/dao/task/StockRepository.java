package com.bsuir.diploma.bonup.dao.task;

import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.task.Stock;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByOrganization(Organization organization);
    Optional<Stock> findByIdAndOrganization(Long id, Organization organization);
    List<Stock> findAllByCategoryInAndDateToGreaterThanEqual(List<Category> categories, Calendar date, Pageable pageable);
}

