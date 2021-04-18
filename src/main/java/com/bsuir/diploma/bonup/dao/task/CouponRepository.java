package com.bsuir.diploma.bonup.dao.task;

import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.task.Coupon;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import com.bsuir.diploma.bonup.model.task.additional.Type;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByOrganizationAndId(Organization organization, Long id);
    List<Coupon> findAllByOrganizationAndType(Organization organization, Type type);
    List<Coupon> findAllByOrganization(Organization organization);
    List<Coupon> findAllByCategoryInAndDateToGreaterThanEqual(List<Category> categories, Calendar date, Pageable pageable);
}
