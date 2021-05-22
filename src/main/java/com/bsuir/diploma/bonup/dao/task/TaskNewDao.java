package com.bsuir.diploma.bonup.dao.task;

import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.organization.OrganizationNew;
import com.bsuir.diploma.bonup.model.task.Task;
import com.bsuir.diploma.bonup.model.task.TaskNew;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import com.bsuir.diploma.bonup.model.task.additional.Type;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskNewDao extends JpaRepository<TaskNew, Long> {
    Optional<TaskNew> findByOrganizationNewAndId(OrganizationNew organizationNew, Long id);
    List<TaskNew> findAllByOrganizationNew(OrganizationNew organizationNew);
    List<TaskNew> findAllByCategoryInAndDateToGreaterThanEqual(List<Category> categories, Calendar date);
    List<TaskNew> findAllByOrganizationNewAndDateToGreaterThanEqual(OrganizationNew organizationNew, Calendar date);
}
