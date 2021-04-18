package com.bsuir.diploma.bonup.model.translation;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "language")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true, exclude = {})
public class Language extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @NonNull
    private String lang;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

}
