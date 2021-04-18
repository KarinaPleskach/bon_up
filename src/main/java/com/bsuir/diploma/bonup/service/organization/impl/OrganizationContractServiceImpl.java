package com.bsuir.diploma.bonup.service.organization.impl;

import com.bsuir.diploma.bonup.dao.organization.ContractRepository;
import com.bsuir.diploma.bonup.model.organization.Contract;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.service.organization.OrganizationContractService;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganizationContractServiceImpl implements OrganizationContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public int limitOfStocks(Calendar date, Organization organization) {
        int limit = 0;
        List<Contract> contracts = contractRepository.findByOrganization(organization);
        for (Contract contract : contracts) {
            if ((date.after(contract.getDateFrom()) && date.before(contract.getDateTo()))
                    || equalCalendar(date, contract.getDateFrom())
                    || equalCalendar(date, contract.getDateTo())) {
                limit += contract.getNumberOfFacilities().getStocks();
            }
        }
        return limit;
    }

    @Override
    public int limitOfHeavyTasks(Calendar date, Organization organization) {
        int limit = 0;
        List<Contract> contracts = contractRepository.findByOrganization(organization);
        for (Contract contract : contracts) {
            if ((date.after(contract.getDateFrom()) && date.before(contract.getDateTo()))
                    || equalCalendar(date, contract.getDateFrom())
                    || equalCalendar(date, contract.getDateTo())) {
                limit += contract.getNumberOfFacilities().getHeavyTasks();
            }
        }
        return limit;
    }

    @Override
    public int limitOfMediumTasks(Calendar date, Organization organization) {
        int limit = 0;
        List<Contract> contracts = contractRepository.findByOrganization(organization);
        for (Contract contract : contracts) {
            if ((date.after(contract.getDateFrom()) && date.before(contract.getDateTo()))
                    || equalCalendar(date, contract.getDateFrom())
                    || equalCalendar(date, contract.getDateTo())) {
                limit += contract.getNumberOfFacilities().getMediumTasks();
            }
        }
        return limit;
    }

    @Override
    public int limitOfEasyTasks(Calendar date, Organization organization) {
        int limit = 0;
        List<Contract> contracts = contractRepository.findByOrganization(organization);
        for (Contract contract : contracts) {
            if ((date.after(contract.getDateFrom()) && date.before(contract.getDateTo()))
                    || equalCalendar(date, contract.getDateFrom())
                    || equalCalendar(date, contract.getDateTo())) {
                limit += contract.getNumberOfFacilities().getEasyTasks();
            }
        }
        return limit;
    }

    @Override
    public int limitOfHeavyCoupons(Calendar date, Organization organization) {
        int limit = 0;
        List<Contract> contracts = contractRepository.findByOrganization(organization);
        for (Contract contract : contracts) {
            if ((date.after(contract.getDateFrom()) && date.before(contract.getDateTo()))
                    || equalCalendar(date, contract.getDateFrom())
                    || equalCalendar(date, contract.getDateTo())) {
                limit += contract.getNumberOfFacilities().getHeavyCoupons();
            }
        }
        return limit;
    }

    @Override
    public int limitOfMediumCoupons(Calendar date, Organization organization) {
        int limit = 0;
        List<Contract> contracts = contractRepository.findByOrganization(organization);
        for (Contract contract : contracts) {
            if ((date.after(contract.getDateFrom()) && date.before(contract.getDateTo()))
                    || equalCalendar(date, contract.getDateFrom())
                    || equalCalendar(date, contract.getDateTo())) {
                limit += contract.getNumberOfFacilities().getMediumCoupons();
            }
        }
        return limit;
    }

    @Override
    public int limitOfEasyCoupons(Calendar date, Organization organization) {
        int limit = 0;
        List<Contract> contracts = contractRepository.findByOrganization(organization);
        for (Contract contract : contracts) {
            if ((date.after(contract.getDateFrom()) && date.before(contract.getDateTo()))
                    || equalCalendar(date, contract.getDateFrom())
                    || equalCalendar(date, contract.getDateTo())) {
                limit += contract.getNumberOfFacilities().getEasyCoupons();
            }
        }
        return limit;
    }

    private boolean equalCalendar(Calendar dateTo, Calendar instance) {
        return dateTo.get(Calendar.YEAR) == instance.get(Calendar.YEAR)
                && dateTo.get(Calendar.MONTH) == instance.get(Calendar.MONTH)
                && dateTo.get(Calendar.DAY_OF_MONTH) == instance.get(Calendar.DAY_OF_MONTH);
    }

}
