package com.bsuir.diploma.bonup.dao.translation;

import com.bsuir.diploma.bonup.model.translation.LanguageKey;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageKeyRepository extends JpaRepository<LanguageKey, Long> {
    Optional<LanguageKey> findByKey(String key);
}

