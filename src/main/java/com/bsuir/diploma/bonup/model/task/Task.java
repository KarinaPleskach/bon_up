package com.bsuir.diploma.bonup.model.task;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import com.bsuir.diploma.bonup.model.task.additional.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "task")
@NoArgsConstructor @Getter @Setter @AllArgsConstructor @Builder
@ToString(callSuper = true, exclude = {})
public class Task extends AbstractEntity {

    @Column(nullable = false, name = "name_key")
    @NonNull
    private String nameKey;

    @Column(name = "date_from", nullable = false)
    @NonNull
    @Temporal(TemporalType.DATE)
    private Calendar dateFrom;

    @Column(name = "date_to", nullable = false)
    @NonNull
    @Temporal(TemporalType.DATE)
    private Calendar dateTo;

    @Column(nullable = false, name = "description_key")
    @NonNull
    private String descriptionKey;

    @Column
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Category category;

    @Column(nullable = false)
    @NonNull
    private Boolean activity;

    @ManyToMany
    @JoinTable(name = "task_photo",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> photos = new ArrayList<>();
}
