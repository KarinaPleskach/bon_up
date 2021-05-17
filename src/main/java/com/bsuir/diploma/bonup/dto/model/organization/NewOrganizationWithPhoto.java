package com.bsuir.diploma.bonup.dto.model.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewOrganizationWithPhoto {
    private Long id;
    private String title;
    private String descriptionText;
    private String directorFirstName;
    private String directorSecondName;
    private String directorLastName;
    private String locationCountry;
    private String locationCity;
    private String locationStreet;
    private String locationHomeNumber;
    private Float latitude;
    private Float longitude;
    private String contactsPhone;
    private String contactsVK;
    private String contactsWebSite;
    private Integer availableTasksCount;
    private Integer availableCouponsCount;
    private Integer availableStocksCount;
    private Long categoryId;
    private Long photoId;
}
