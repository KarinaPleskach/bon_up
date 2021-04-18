package com.bsuir.diploma.bonup.dto.model.organization;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class OrganizationUserDto {
    private String name;
    private String description;
    private String city;
    private String country;
    private Float x;
    private Float y;
    private List<Long> photos = new ArrayList<>();
}
