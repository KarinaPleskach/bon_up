package com.bsuir.diploma.bonup.dao.translation;

import com.bsuir.diploma.bonup.model.translation.Language;
import com.bsuir.diploma.bonup.model.translation.LanguageKey;
import com.bsuir.diploma.bonup.model.translation.LanguageTranslation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageTranslationRepository extends JpaRepository<LanguageTranslation, Long> {
    Optional<LanguageTranslation> findByLanguageAndLanguageKey(Language language, LanguageKey languageKey);
}
