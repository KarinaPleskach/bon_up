package com.bsuir.diploma.bonup.dto.model.task.task;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PublicTaskDto {
    private Long id;
    private String name;
    private String dateFrom;
    private String dateTo;
    private String description;
    private Integer count;

    private String organizationName;
    private Long subcategoryId;
    private String subcategory;
    private Long categoryId;
    private String category;

    private Long typeId;
    private String type;
    private Integer pointsCount;

    private Boolean activity;
    private Boolean accepted;
    private Boolean rejected;
    private Boolean done;

    private List<Long> photos = new ArrayList<>();
}
