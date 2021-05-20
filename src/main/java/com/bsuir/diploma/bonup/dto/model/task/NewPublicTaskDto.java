package com.bsuir.diploma.bonup.dto.model.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewPublicTaskDto {
    private Long id;
    private String name;
    private String dateFrom;
    private String dateTo;
    private String description;
    private String organizationName;
    private Long categoryId;
    private Long photoId;
}
