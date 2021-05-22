alter table bought_user_profile_coupon
    drop constraint bought_user_profile_task_to_task_fk;
alter table bought_user_profile_coupon
    add constraint profile_new_coupon_fk foreign key (coupon_id) references coupon_new (id);

alter table done_user_profile_coupon
    drop constraint done_user_profile_coupon_to_coupon_fk;
alter table done_user_profile_coupon
    add constraint profile_done_new_coupon_fk foreign key (coupon_id) references coupon_new (id);