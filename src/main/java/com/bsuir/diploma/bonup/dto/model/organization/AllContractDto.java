package com.bsuir.diploma.bonup.dto.model.organization;

import java.util.Calendar;
import lombok.Builder;
import lombok.Data;

@Data
public class AllContractDto {
    private Long id;

    private String dateFrom;
    private String dateTo;

    private Integer stocks;
    private Integer heavyTasks;
    private Integer mediumTasks;
    private Integer easyTasks;
    private Integer heavyCoupons;
    private Integer mediumCoupons;
    private Integer easyCoupons;

    private Boolean paid;
    private Long costId;

    @Builder
    public AllContractDto(Long id, String dateFrom, String dateTo, Integer stocks, Integer heavyTasks, Integer mediumTasks, Integer easyTasks, Integer heavyCoupons, Integer mediumCoupons, Integer easyCoupons, Boolean paid, Long costId) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.stocks = stocks;
        this.heavyTasks = heavyTasks;
        this.mediumTasks = mediumTasks;
        this.easyTasks = easyTasks;
        this.heavyCoupons = heavyCoupons;
        this.mediumCoupons = mediumCoupons;
        this.easyCoupons = easyCoupons;
        this.paid = paid;
        this.costId = costId;
    }
}
