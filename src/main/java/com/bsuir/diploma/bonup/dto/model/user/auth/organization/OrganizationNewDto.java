package com.bsuir.diploma.bonup.dto.model.user.auth.organization;

import lombok.Data;

@Data
public class OrganizationNewDto {
    private String token;
    private String title;
    private String descriptionText;
    private String directorFirstName;
    private String directorSecondName;
    private String directorLastName;
    private String address;
    private Double latitude;
    private Double longitude;
    private String contactsPhone;
    private String contactsVK;
    private String contactsWebSite;
    private Integer availableTasksCount;
    private Integer availableCouponsCount;
    private Integer availableStocksCount;
    private Long categoryId;
    private Long photoId;
}
