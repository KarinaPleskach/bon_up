create table beloved_user_profile_stock (
    user_profile_id bigint not null,
    stock_id bigint not null,
    constraint profiles_stocks_profiles_fk foreign key (user_profile_id) references user_profile (id),
    constraint profiles_stocks_stocks_fk foreign key (stock_id) references stock (id),
    constraint profiles_stocks_uq unique (user_profile_id, stock_id)
);