package com.bsuir.diploma.bonup.dto.model.task.task;

import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class TaskDto {
    private String name;
    private Calendar dateFrom;
    private Calendar dateTo;
    private String description;
    private Integer count;

    private String organizationName;
    private Long subcategoryId;
    private Long typeId;

    private String token;
    private String langKey;
    private Boolean activity;
}
