create table coupon (
   id bigserial primary key,
   name_key character varying(255) not null,
   description_key character varying(255) not null,
   organization_id bigint not null,
   subcategory_id bigint not null,
   date_from date not null,
   date_to date not null,
   type_id bigint not null,
   count int,
   activity boolean not null,
   constraint coupons_types_fk foreign key (type_id) references type (id),
   constraint coupons_categories_fk foreign key (subcategory_id) references category (id)
);

create table coupon_photo (
   photo_id bigint not null,
   coupon_id bigint not null,
   constraint coupon_photo_coupon_id_photo_id_uq unique (photo_id, coupon_id),
   constraint coupon_photo_to_photo_fk foreign key (photo_id) references photo (id),
   constraint coupon_photo_to_coupon_fk foreign key (coupon_id) references coupon (id)
);

create table beloved_user_profile_coupon (
    user_profile_id bigint not null,
    coupon_id bigint not null,
    constraint beloved_user_profile_coupon_to_user_profile_fk foreign key (user_profile_id) references user_profile (id),
    constraint beloved_user_profile_coupon_to_coupon_fk foreign key (coupon_id) references coupon (id),
    constraint beloved_profile_coupon_uq unique (user_profile_id, coupon_id)
);

create table bought_user_profile_coupon (
    user_profile_id bigint not null,
    coupon_id bigint not null,
    constraint bought_user_profile_task_to_user_profile_fk foreign key (user_profile_id) references user_profile (id),
    constraint bought_user_profile_task_to_task_fk foreign key (coupon_id) references coupon (id),
    constraint bought_profiles_tasks_uq unique (user_profile_id, coupon_id)
);

create table done_user_profile_coupon (
    user_profile_id bigint not null,
    coupon_id bigint not null,
    constraint done_user_profile_coupon_to_user_profile_fk foreign key (user_profile_id) references user_profile (id),
    constraint done_user_profile_coupon_to_coupon_fk foreign key (coupon_id) references coupon (id),
    constraint done_profile_coupon_uq unique (user_profile_id, coupon_id)
);
