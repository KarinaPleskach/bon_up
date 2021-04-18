package com.bsuir.diploma.bonup.dto.converter.organization;

import com.bsuir.diploma.bonup.dto.model.organization.AllContractDto;
import com.bsuir.diploma.bonup.model.organization.Contract;
import com.bsuir.diploma.bonup.model.organization.NumberOfFacilities;
import java.text.SimpleDateFormat;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ContractToAllContractDtoConverter implements Converter<Contract, AllContractDto> {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public AllContractDto convert(Contract contract) {
        NumberOfFacilities numberOfFacilities = contract.getNumberOfFacilities();
        return AllContractDto.builder()
                .dateFrom(format.format(contract.getDateFrom().getTime()))
                .dateTo(format.format(contract.getDateTo().getTime()))
                .paid(contract.getPaid())
                .costId(contract.getCost() == null ? null : contract.getCost().getId())
                .stocks(numberOfFacilities.getStocks())
                .heavyCoupons(numberOfFacilities.getHeavyCoupons())
                .easyCoupons(numberOfFacilities.getEasyCoupons())
                .easyTasks(numberOfFacilities.getEasyTasks())
                .heavyTasks(numberOfFacilities.getHeavyTasks())
                .id(contract.getId())
                .mediumCoupons(numberOfFacilities.getMediumCoupons())
                .mediumTasks(numberOfFacilities.getMediumTasks())
                .build();
    }

}
