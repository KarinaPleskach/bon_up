package com.bsuir.diploma.bonup.model.organization;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employee")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true)
public class Employee extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_login_id", nullable = false)
    @NonNull
    private UserLogin userLogin;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    @NonNull
    private Organization organization;

    public Employee(@NonNull UserLogin userLogin, @NonNull Organization organization) {
        this.userLogin = userLogin;
        this.organization = organization;
    }

}
