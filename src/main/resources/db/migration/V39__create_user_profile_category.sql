create table user_profile_category (
                                    user_profile_id bigint not null,
                                    category_id bigint not null,
                                    constraint user_profile_photo_user_profile_id_photo_id_uq unique (user_profile_id, category_id),
                                    constraint user_profile_photo_to_user_profile_fk foreign key (user_profile_id) references user_profile (id),
                                    constraint user_profile_photo_to_photo_fk foreign key (category_id) references category (id)
);