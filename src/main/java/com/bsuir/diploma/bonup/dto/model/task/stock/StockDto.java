package com.bsuir.diploma.bonup.dto.model.task.stock;

import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class StockDto {
    private String name;
    private Calendar dateFrom;
    private Calendar dateTo;
    private String description;

    private String organizationName;
    private Long subcategoryId;

    private String token;
    private String langKey;
    private Boolean activity;
}
