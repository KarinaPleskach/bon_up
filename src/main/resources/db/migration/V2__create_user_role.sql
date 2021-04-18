create table  role (
    id bigserial primary key,
    description character varying(45) not null,
    constraint roles_description_uq unique (description)
);

create table  user_role (
    user_login_id bigint not null,
    role_id bigint not null,
    constraint user_role_user_login_fk foreign key (user_login_id) references user_login (id),
    constraint user_role_role_fk foreign key (role_id) references role (id),
    constraint user_role_ids_uq unique (user_login_id, role_id)
);

insert into role (description) values
    ('ROLE_USER'),
    ('ROLE_EMPLOYEE'),
    ('ROLE_ORGANIZATION_ADMIN'),
    ('ROLE_GLOBAL_ADMIN');