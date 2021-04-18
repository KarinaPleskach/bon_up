CREATE TABLE user_mail (
    id bigserial primary key,
    verify_mail boolean not null,
    mail_code character varying(6),
    user_login_id bigint not null,
    constraint user_mail_user_login_fk foreign key (user_login_id) references user_login (id),
    constraint user_mail_user_login_id_uq unique (user_login_id)
);
