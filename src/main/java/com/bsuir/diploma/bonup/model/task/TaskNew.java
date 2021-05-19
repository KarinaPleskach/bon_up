package com.bsuir.diploma.bonup.model.task;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.organization.OrganizationNew;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "task_new")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString(callSuper = true, exclude = {})
public class TaskNew extends AbstractEntity {

    @Column(nullable = false, name = "title")
    @NonNull
    private String title;

    @Column(name = "date_from", nullable = false)
    @NonNull
    @Temporal(TemporalType.DATE)
    private Calendar dateFrom;

    @Column(name = "date_to", nullable = false)
    @NonNull
    @Temporal(TemporalType.DATE)
    private Calendar dateTo;

    @Column(nullable = false, name = "description")
    @NonNull
    private String description;

    @Column
    private Integer count;

    @Column
    private Integer bonus;

    @ManyToOne
    @JoinColumn(name = "organization_new_id")
    private OrganizationNew organizationNew;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "photoid")
    private Photo photo;
}
