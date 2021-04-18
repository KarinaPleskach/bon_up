package com.bsuir.diploma.bonup.dao.organization;

import com.bsuir.diploma.bonup.model.organization.NumberOfFacilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberOfFacilitiesRepository extends JpaRepository<NumberOfFacilities, Long> {
}
