package com.bsuir.diploma.bonup.model.task;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "stock")
@NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter
@ToString(callSuper = true, exclude = {})
public class Stock extends AbstractEntity {

    @Column(nullable = false, name = "name_key")
    private String nameKey;

    @Column(nullable = false, name = "description_key")
    private String descriptionKey;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Category category;

    @Column(name = "date_from", nullable = false)
    @NonNull
    @Temporal(TemporalType.DATE)
    private Calendar dateFrom;

    @Column(name = "date_to", nullable = false)
    @NonNull
    @Temporal(TemporalType.DATE)
    private Calendar dateTo;

    @Column(nullable = false)
    @NonNull
    private Boolean activity;

    @ManyToMany
    @JoinTable(name = "stock_photo",
            joinColumns = @JoinColumn(name = "stock_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> photos = new ArrayList<>();

}
