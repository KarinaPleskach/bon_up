package com.bsuir.diploma.bonup.model.additional;

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
@Table(name = "city")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true)
public class City extends AbstractEntity {

    @Column(name = "language_key", nullable = false)
    @NonNull
    private String languageKey;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @NonNull
    private Country country;

}
