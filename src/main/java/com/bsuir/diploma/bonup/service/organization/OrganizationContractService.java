package com.bsuir.diploma.bonup.service.organization;

import com.bsuir.diploma.bonup.model.organization.Organization;
import java.util.Calendar;

public interface OrganizationContractService {
    int limitOfStocks(Calendar date, Organization organization);

    int limitOfHeavyTasks(Calendar date, Organization organization);

    int limitOfMediumTasks(Calendar date, Organization organization);

    int limitOfEasyTasks(Calendar date, Organization organization);

    int limitOfHeavyCoupons(Calendar date, Organization organization);

    int limitOfMediumCoupons(Calendar date, Organization organization);

    int limitOfEasyCoupons(Calendar date, Organization organization);
}
