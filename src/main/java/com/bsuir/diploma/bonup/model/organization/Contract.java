package com.bsuir.diploma.bonup.model.organization;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import com.bsuir.diploma.bonup.model.additional.Cost;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "organization_contract")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true)
public class Contract extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    @NonNull
    private Organization organization;

    @Column(name = "date_from", nullable = false)
    @NonNull
    @Temporal(TemporalType.DATE)
    private Calendar dateFrom;

    @Column(name = "date_to", nullable = false)
    @NonNull
    @Temporal(TemporalType.DATE)
    private Calendar dateTo;

    @Column
    @NonNull
    private Boolean paid;

    @ManyToOne
    @JoinColumn(name = "cost_id")
    private Cost cost;

    @ManyToOne
    @JoinColumn(name = "number_of_facilities_id", nullable = false)
    @NonNull
    private NumberOfFacilities numberOfFacilities;

    @Builder
    public Contract(@NonNull Organization organization, @NonNull Calendar dateFrom, @NonNull Calendar dateTo, @NonNull Boolean paid, Cost cost, @NonNull NumberOfFacilities numberOfFacilities) {
        this.organization = organization;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.paid = paid;
        this.cost = cost;
        this.numberOfFacilities = numberOfFacilities;
    }
}
