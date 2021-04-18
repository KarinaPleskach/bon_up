package com.bsuir.diploma.bonup.dao.translation;

import com.bsuir.diploma.bonup.model.translation.Language;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByLang(String lang);
}
