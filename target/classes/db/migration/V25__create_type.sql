create table type (
    id bigserial primary key,
    key character varying(45) not null,
    points_count int not null,
    cost_count int not null,
    constraint types_key_uq unique (key)
);

insert into type (key, points_count, cost_count) values
    ('heavy', 50, 400),
    ('medium', 25, 200),
    ('easy', 10, 150);
