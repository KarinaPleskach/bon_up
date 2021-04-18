package com.bsuir.diploma.bonup.model.organization;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import com.bsuir.diploma.bonup.model.additional.City;
import com.bsuir.diploma.bonup.model.photo.Photo;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "organization")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true)
public class Organization extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @Column(nullable = false, name = "x_coor")
    @NonNull
    private Float xCoor;

    @Column(nullable = false, name = "y_coor")
    @NonNull
    private Float yCoor;

    @Column(name = "description_key")
    private String descriptionKey;

    @ManyToOne
    @JoinColumn(name = "user_login_id", nullable = false)
    @NonNull
    private UserLogin userLogin;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @NonNull
    private City city;

    @ManyToMany
    @JoinTable(name = "organization_photo",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> photos = new ArrayList<>();


    public Organization(@NonNull String name, @NonNull City city,
                        @NonNull Float x, @NonNull Float y,
                        @NonNull UserLogin userLogin) {
        this.name = name;
        this.city = city;
        this.xCoor = x;
        this.yCoor = y;
        this.userLogin = userLogin;
    }

}
