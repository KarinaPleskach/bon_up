package com.bsuir.diploma.bonup.model.organization;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "organization_new")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class OrganizationNew extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @NonNull
    private String title;

    @Column(nullable = false, name = "descriptiontext")
    private String descriptionText;

    @Column(nullable = false, name = "directorfirstname")
    private String directorFirstName;

    @Column(nullable = false, name = "directorsecondname")
    private String directorSecondName;

    @Column(nullable = false, name = "directorlastname")
    private String directorLastName;

    @Column(nullable = false, name = "locationcountry")
    private String locationCountry;

    @Column(nullable = false, name = "locationcity")
    private String locationCity;

    @Column(nullable = false, name = "locationstreet")
    private String locationStreet;

    @Column(nullable = false, name = "locationhomenumber")
    private String locationHomeNumber;

    @Column(nullable = false, name = "contactsphone")
    private String contactsPhone;

    @Column(nullable = false, name = "contactsvk")
    private String contactsVK;

    @Column(nullable = false, name = "contactswebsite")
    private String contactsWebSite;

    @Column(nullable = false)
    @NonNull
    private Float latitude = 0.0f;

    @Column(nullable = false)
    @NonNull
    private Float longitude = 0.0f;

    @Column(nullable = false, name = "availabletaskscount")
    private Integer availableTasksCount;

    @Column(nullable = false, name = "availablecouponscount")
    private Integer availableCouponsCount;

    @Column(nullable = false, name = "availablestockscount")
    private Integer availableStocksCount;

    @ManyToOne
    @JoinColumn(name = "user_login_id", nullable = false)
    @NonNull
    private UserLogin userLogin;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "photoid")
    private Photo photo;

    @Builder
    public OrganizationNew(@NonNull String title, String descriptionText, String directorFirstName, String directorSecondName, String directorLastName, String locationCountry, String locationCity, String locationStreet, String locationHomeNumber, String contactsPhone, String contactsVK, String contactsWebSite, Integer availableTasksCount, Integer availableCouponsCount, Integer availableStocksCount, @NonNull UserLogin userLogin, Category category, Photo photo) {
        this.title = title;
        this.descriptionText = descriptionText;
        this.directorFirstName = directorFirstName;
        this.directorSecondName = directorSecondName;
        this.directorLastName = directorLastName;
        this.locationCountry = locationCountry;
        this.locationCity = locationCity;
        this.locationStreet = locationStreet;
        this.locationHomeNumber = locationHomeNumber;
        this.contactsPhone = contactsPhone;
        this.contactsVK = contactsVK;
        this.contactsWebSite = contactsWebSite;
        this.availableTasksCount = availableTasksCount;
        this.availableCouponsCount = availableCouponsCount;
        this.availableStocksCount = availableStocksCount;
        this.userLogin = userLogin;
        this.category = category;
        this.photo = photo;
    }
}
