package com.bsuir.diploma.bonup.dao.task;

import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.task.Task;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import com.bsuir.diploma.bonup.model.task.additional.Type;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByOrganizationAndId(Organization organization, Long id);
    List<Task> findAllByOrganizationAndType(Organization organization, Type type);
    List<Task> findAllByOrganization(Organization organization);
    List<Task> findAllByCategoryInAndDateToGreaterThanEqual(List<Category> categories, Calendar date, Pageable pageable);
}

