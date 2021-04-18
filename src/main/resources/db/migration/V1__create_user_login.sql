create table user_login (
    id bigserial primary key,
    login character varying(255) not null,
    password character varying(255) not null,
    token character varying(300),
    constraint user_login_login_uq unique (login),
    constraint user_login_token_uq unique (token)
);
