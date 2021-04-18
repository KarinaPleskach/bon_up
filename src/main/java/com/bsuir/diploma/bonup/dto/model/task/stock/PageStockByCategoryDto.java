package com.bsuir.diploma.bonup.dto.model.task.stock;

import lombok.Data;

@Data
public class PageStockByCategoryDto {
    private Long categoryId;
    private Integer page;
    private Integer number;
    private String token;
}
