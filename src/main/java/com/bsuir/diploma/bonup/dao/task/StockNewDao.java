package com.bsuir.diploma.bonup.dao.task;

import com.bsuir.diploma.bonup.model.organization.OrganizationNew;
import com.bsuir.diploma.bonup.model.task.StockNew;
import com.bsuir.diploma.bonup.model.task.TaskNew;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockNewDao extends JpaRepository<StockNew, Long> {
    Optional<StockNew> findByOrganizationNewAndId(OrganizationNew organizationNew, Long id);
    List<StockNew> findAllByOrganizationNew(OrganizationNew organizationNew);
}
