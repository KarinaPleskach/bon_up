package com.bsuir.diploma.bonup.dao.additional;

import com.bsuir.diploma.bonup.model.additional.Currency;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByReduction(String reduction);
    Optional<Currency> findByLanguageKey(String languageKey);
}
