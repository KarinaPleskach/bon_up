package com.bsuir.diploma.bonup.model.user;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_login")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true, exclude = {})
public class UserLogin extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @NonNull
    private String login;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Column(unique = true)
    private String token;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_login_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public UserLogin(@NonNull String login, @NonNull String password) {
        this.login = login;
        this.password = password;
    }

    @Builder
    public UserLogin(String login, String password, String token) {
        this(login, password);
        this.token = token;
    }

}
