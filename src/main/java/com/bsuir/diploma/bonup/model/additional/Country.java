package com.bsuir.diploma.bonup.model.additional;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import com.bsuir.diploma.bonup.model.photo.Photo;
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
@Table(name = "country")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true)
public class Country extends AbstractEntity {

    @Column(name = "language_key", nullable = false)
    @NonNull
    private String languageKey;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    @NonNull
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;


}
