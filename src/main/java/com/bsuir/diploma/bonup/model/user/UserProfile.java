package com.bsuir.diploma.bonup.model.user;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.Coupon;
import com.bsuir.diploma.bonup.model.task.Stock;
import com.bsuir.diploma.bonup.model.task.Task;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_profile")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true, exclude = {})
public class UserProfile extends AbstractEntity {

    @Column(nullable = false)
    @NonNull
    private String name;

    @Column(nullable = false)
    @NonNull
    private Long points;

    @OneToOne
    @JoinColumn(name = "user_login_id", unique = true, nullable = false)
    @NonNull
    private UserLogin userLogin;

    @ManyToMany
    @JoinTable(name = "beloved_user_profile_stock",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private Set<Stock> belovedStocks = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "accepted_user_profile_task",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> acceptedTasks = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "rejected_user_profile_task",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> rejectedTasks = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "done_user_profile_task",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> doneTasks = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "beloved_user_profile_coupon",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    private Set<Coupon> belovedCoupons = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "bought_user_profile_coupon",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    private Set<Coupon> boughtCoupons = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "done_user_profile_coupon",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    private Set<Coupon> doneCoupons = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_profile_photo",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> photos = new ArrayList<>();

    public UserProfile(@NonNull String name, UserLogin userLogin) {
        this.name = name;
        this.userLogin = userLogin;
    }

    @Builder
    public UserProfile(@NonNull String name, UserLogin userLogin, Long points) {
        this.name = name;
        this.userLogin = userLogin;
        this.points = points;
    }

}
