create table organization_contract (
    id bigserial primary key,
    organization_id bigint not null,
    date_from date not null,
    date_to date not null,
    paid boolean not null,
    cost_id bigint,
    number_of_facilities_id bigint not null,

    constraint organization_contract_to_organization_fk foreign key (organization_id) references organization (id),
    constraint organization_contract_to_cost_fk foreign key (cost_id) references cost (id),
    constraint organization_contract_to_number_of_facilities_fk foreign key (number_of_facilities_id) references number_of_facilities (id)
);
