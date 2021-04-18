package com.bsuir.diploma.bonup.service.admin.organization;

import com.bsuir.diploma.bonup.dto.model.organization.AllContractDto;
import com.bsuir.diploma.bonup.dto.model.organization.admin.AdminNameDto;
import com.bsuir.diploma.bonup.dto.model.organization.admin.ContractDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.AdminAuthUser;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.NewOrganization;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.OldAdminAuthUser;
import java.util.List;

public interface AdminOrganizationService {
    String createNewUser(AdminAuthUser adminAuthUser, String lang);
    String getUserOrganizationToken(OldAdminAuthUser oldAdminAuthUser, String lang);
    void createOrganizationForUser(NewOrganization newOrganization, String lang);
    void setContract(ContractDto contractDto, String lang);
    List<AllContractDto> getContracts(AdminNameDto adminNameDto, String lang);
    void updateContract(ContractDto contractDto, Long contractId, String lang);
}
