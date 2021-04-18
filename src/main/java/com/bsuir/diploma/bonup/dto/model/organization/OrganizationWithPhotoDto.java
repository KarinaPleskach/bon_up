package com.bsuir.diploma.bonup.dto.model.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class OrganizationWithPhotoDto {
    private String name;
    private String city;
    private String country;
    private Long photoId;
}
