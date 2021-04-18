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
@Table(name = "cost")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true)
public class Cost extends AbstractEntity {

    @Column(nullable = false)
    @NonNull
    private Double value;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    @NonNull
    private Currency currency;

}
