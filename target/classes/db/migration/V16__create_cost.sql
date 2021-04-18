create table cost(
    id bigserial primary key,
    currency_id bigint not null,
    value double precision not null,
    constraint cost_to_currency_fk foreign key (currency_id) references currency (id)
);
