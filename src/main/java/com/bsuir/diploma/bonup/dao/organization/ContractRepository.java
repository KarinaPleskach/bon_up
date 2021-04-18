package com.bsuir.diploma.bonup.dao.organization;

import com.bsuir.diploma.bonup.model.organization.Contract;
import com.bsuir.diploma.bonup.model.organization.Organization;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByOrganization(Organization organization);
    Optional<Contract> findByOrganizationAndId(Organization organization, Long id);
}
