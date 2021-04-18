package com.bsuir.diploma.bonup.service.translation.impl;

import com.bsuir.diploma.bonup.dao.translation.LanguageKeyRepository;
import com.bsuir.diploma.bonup.dao.translation.LanguageRepository;
import com.bsuir.diploma.bonup.dao.translation.LanguageTranslationRepository;
import com.bsuir.diploma.bonup.exception.translation.NoSuchLanguageException;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private LanguageTranslationRepository languageTranslationRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private LanguageKeyRepository languageKeyRepository;

    @Override
    public String getMessage(String key, String lang) {
        return languageTranslationRepository.findByLanguageAndLanguageKey(
                languageRepository.findByLang(lang)
                        .orElseThrow(() -> new NoSuchLanguageException(lang)),
                languageKeyRepository.findByKey(key)
                        .orElseThrow(() -> new NoSuchLanguageException(lang)))
                .orElseThrow(() -> new NoSuchLanguageException(lang))
                .getValue();
    }

}
