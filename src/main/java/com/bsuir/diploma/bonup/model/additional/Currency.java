package com.bsuir.diploma.bonup.model.additional;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "currency")
@NoArgsConstructor @Getter @Setter
public class Currency extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @NonNull
    private String reduction;

    @Column(nullable = false, unique = true, name = "language_key")
    @NonNull
    private String languageKey;

    @Column(nullable = false)
    @NonNull
    private String symbol;

    @Builder
    public Currency(String reduction, String languageKey, String symbol) {
        this.reduction = reduction;
        this.languageKey = languageKey;
        this.symbol = symbol;
    }

}
