package com.bsuir.diploma.bonup.dto.model.task.additional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class PublicTypeDto {
    private Long id;
    private String description;
    private Integer pointsCount;
    private Integer costCount;
}
