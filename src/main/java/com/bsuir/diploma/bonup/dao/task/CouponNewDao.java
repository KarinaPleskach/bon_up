package com.bsuir.diploma.bonup.dao.task;

import com.bsuir.diploma.bonup.model.organization.OrganizationNew;
import com.bsuir.diploma.bonup.model.task.CouponNew;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponNewDao extends JpaRepository<CouponNew, Long> {
    Optional<CouponNew> findByOrganizationNewAndId(OrganizationNew organizationNew, Long id);
    List<CouponNew> findAllByOrganizationNew(OrganizationNew organizationNew);
    List<CouponNew> findAllByCategoryInAndDateToGreaterThanEqual(List<Category> categories, Calendar date);
    List<CouponNew> findAllByOrganizationNewAndDateToGreaterThanEqual(OrganizationNew organizationNew, Calendar date);
}