package com.bsuir.diploma.bonup.dto.model.task;

import com.bsuir.diploma.bonup.dto.model.organization.NewOrganizationWithPhoto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinishedTaskNewDto {
    private NewOrganizationWithPhoto organization;

    private Long id;
    private String name;
    private String dateFrom;
    private String dateTo;
    private String description;
    private Long categoryId;
    private Integer bonusesCount;
    private Long photoId;

    private Boolean isDied;
    private Boolean isResolved;
}
