package com.bsuir.diploma.bonup.model.task.additional;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "category")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
@ToString(callSuper = true)
public class Category extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @NonNull
    private String key;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category category;

}
