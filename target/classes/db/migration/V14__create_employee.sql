create table employee (
    id bigserial primary key,
    user_login_id bigint not null,
    organization_id bigint not null,
    constraint employees_organizations_fk foreign key (organization_id) references organization (id),
    constraint employees_users_fk foreign key (user_login_id) references user_login (id)
);
