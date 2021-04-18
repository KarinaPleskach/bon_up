package com.bsuir.diploma.bonup.dto.model.organization.admin;

import java.util.Calendar;
import lombok.Data;

@Data
public class ContractDto {
    private String name;

    private Calendar dateFrom;
    private Calendar dateTo;

    private Integer stocks;
    private Integer heavyTasks;
    private Integer mediumTasks;
    private Integer easyTasks;
    private Integer heavyCoupons;
    private Integer mediumCoupons;
    private Integer easyCoupons;

    private String adminToken;
    private String userOrganizationToken;
}
