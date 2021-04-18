package com.bsuir.diploma.bonup.model.task.additional;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "type")
@NoArgsConstructor @Getter @Setter @AllArgsConstructor @Builder
@ToString(callSuper = true, exclude = {})
public class Type extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @NonNull
    private String key;

    @Column(name = "points_count", nullable = false)
    @NonNull
    private Integer pointsCount;

    @Column(name = "cost_count", nullable = false)
    @NonNull
    private Integer costCount;

}
