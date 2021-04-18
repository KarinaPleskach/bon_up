package com.bsuir.diploma.bonup.model.organization;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "number_of_facilities")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true)
public class NumberOfFacilities extends AbstractEntity {

    @Column(nullable = false)
    @NonNull
    private Integer stocks;

    @Column(nullable = false, name = "heavy_tasks")
    @NonNull
    private Integer heavyTasks;

    @Column(nullable = false, name = "medium_tasks")
    @NonNull
    private Integer mediumTasks;

    @Column(nullable = false, name = "easy_tasks")
    @NonNull
    private Integer easyTasks;

    @Column(nullable = false, name = "heavy_coupons")
    @NonNull
    private Integer heavyCoupons;

    @Column(nullable = false, name = "medium_coupons")
    @NonNull
    private Integer mediumCoupons;

    @Column(nullable = false, name = "easy_coupons")
    @NonNull
    private Integer easyCoupons;

    @Builder
    public NumberOfFacilities(@NonNull Integer stocks, @NonNull Integer heavyTasks, @NonNull Integer mediumTasks, @NonNull Integer easyTasks,
                              @NonNull Integer heavyCoupons, @NonNull Integer mediumCoupons, @NonNull Integer easyCoupons) {
        this.stocks = stocks;
        this.heavyTasks = heavyTasks;
        this.mediumTasks = mediumTasks;
        this.easyTasks = easyTasks;
        this.heavyCoupons = heavyCoupons;
        this.mediumCoupons = mediumCoupons;
        this.easyCoupons = easyCoupons;
    }

}
