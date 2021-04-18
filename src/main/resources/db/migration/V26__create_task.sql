create table task (
    id bigserial primary key,
    name_key character varying(255) not null,
    description_key character varying(255) not null,
    organization_id bigint not null,
    subcategory_id bigint not null,
    date_from date not null,
    date_to date not null,
    type_id bigint not null,
    activity boolean not null,
    count int,
    constraint tasks_types_fk foreign key (type_id) references type (id),
    constraint tasks_organizations_fk foreign key (organization_id) references organization (id),
    constraint tasks_categories_fk foreign key (subcategory_id) references category (id)
);