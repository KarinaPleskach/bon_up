package com.bsuir.diploma.bonup.model.user;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_mail")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true, exclude = {})
public class UserMail extends AbstractEntity {

    @Column(name = "verify_mail", nullable = false)
    private boolean verifyMail;

    @Column(name = "mail_code")
    private String mailCode;

    @OneToOne
    @JoinColumn(name = "user_login_id", unique = true, nullable = false)
    @NonNull
    private UserLogin userLogin;

    @Builder
    public UserMail(boolean verifyMail, String mailCode, UserLogin userLogin) {
        this.verifyMail = verifyMail;
        this.mailCode = mailCode;
        this.userLogin = userLogin;
    }
}
