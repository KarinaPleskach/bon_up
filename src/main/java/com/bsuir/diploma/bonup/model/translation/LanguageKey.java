package com.bsuir.diploma.bonup.model.translation;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "language_key")
@NoArgsConstructor @Getter @Setter @AllArgsConstructor
@ToString(callSuper = true, exclude = {})
public class LanguageKey extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @NonNull
    private String key;

}
