package com.bsuir.diploma.bonup.dto.model.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskNewDto {
    private String title;
    private String descriptionText;
    private Long categoryId;
    private String token;
    private String organizationName;
    private Integer bonusesCount;
    private Integer allowedCount;
    private Double startDateTimestamp;
    private Double endDateTimestamp;
    private Long photoId;
}
