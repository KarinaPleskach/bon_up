create table organization (
    id bigserial primary key,
    name character varying(255) not null,
    description_key character varying(255),
    city_id bigint not null,
    x_coor real not null,
    y_coor real not null,
    user_login_id bigint not null,
    constraint organizations_users_fk foreign key (user_login_id) references user_login (id),
    constraint organizations_cities_fk foreign key (city_id) references city (id),
    constraint organizations_name_uq unique (name)
);
