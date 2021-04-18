package com.bsuir.diploma.bonup.model.user;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "role")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true, exclude = {})
public class Role extends AbstractEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NonNull
    private UserRole description;

    public Role(@NonNull UserRole description) {
        this.description = description;
    }

}
