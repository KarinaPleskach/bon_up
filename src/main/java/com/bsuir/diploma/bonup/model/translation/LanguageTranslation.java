package com.bsuir.diploma.bonup.model.translation;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "language_translation")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true, exclude = {})
public class LanguageTranslation extends AbstractEntity {

    @Column(nullable = false)
    @NonNull
    private String value;

    @ManyToOne
    @JoinColumn(name = "key_id")
    private LanguageKey languageKey;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    public LanguageTranslation(LanguageKey languageKey, Language language, String value) {
        this.languageKey = languageKey;
        this.language = language;
        this.value = value;
    }
}
