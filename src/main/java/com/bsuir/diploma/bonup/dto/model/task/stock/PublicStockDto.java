package com.bsuir.diploma.bonup.dto.model.task.stock;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PublicStockDto {
    private Long id;
    private String name;
    private String dateFrom;
    private String dateTo;
    private String description;

    private String organizationName;
    private Long subcategoryId;
    private String subcategory;
    private Long categoryId;
    private String category;

    private Boolean activity;
    private Boolean beloved;

    private List<Long> photos = new ArrayList<>();
}
