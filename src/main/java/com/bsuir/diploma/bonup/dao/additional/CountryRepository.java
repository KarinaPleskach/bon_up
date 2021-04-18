package com.bsuir.diploma.bonup.dao.additional;

import com.bsuir.diploma.bonup.model.additional.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
