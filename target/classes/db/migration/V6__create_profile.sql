create table user_profile (
    id bigserial primary key,
    name character varying(45) not null,
    user_login_id bigint not null,
    constraint profiles_users_fk foreign key (user_login_id) references user_login (id),
    constraint profiles_user_id unique (user_login_id)
);

insert into user_login (login, password, token) values
    ('karinapleskach@mail.ru', '3ca3ebf244fe1ea3585f2164c786b087f92655bbeed17b5daa946e8f676ef125', 'eyJhbGciOiJIUzUxMiJ9.eyJjbGllbnRUeXBlIjoidXNlciIsInRva2VuX2V4cGlyYXRpb25fZGF0ZSI6NDc3MzQ3ODk1MjczMCwidXNlcklEIjoiMSIsInVzZXJuYW1lIjoia2FyaW5hcGxlc2thY2hAbWFpbC5ydSIsInRva2VuX2NyZWF0ZV9kYXRlIjoxNjE3ODA1MzUyNzMwfQ.uVkRxmRjyyiMoqiOUGme6ZoCHIzKAjnQ2xNXU_OE0fkjllQezL4avbj-JvkbUyhqHkwGvFgXOTe3Lidya-qYPQ');

insert into user_mail (verify_mail, mail_code, user_login_id) values
    (true, null, 1);

insert into user_profile (name, user_login_id) values
    ('Karina', 1);

insert into user_role (user_login_id, role_id) values
    (1, 1);


insert into user_login (login, password, token) values
('root', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2', 'eyJhbGciOiJIUzUxMiJ9.eyJjbGllbnRUeXBlIjoidXNlciIsInRva2VuX2V4cGlyYXRpb25fZGF0ZSI6NDc0MzMwMzg2MzQ1MSwidXNlcklEIjoxLCJ1c2VybmFtZSI6InJvb3RAbWFpbC5ydSIsInRva2VuX2NyZWF0ZV9kYXRlIjoxNTg3NjMwMjYzNDM0fQ.UnHv_qCrk4iFA8ZRp8l7znefYvChSBzdaATR_RkX1QKjkZFElsC5U-Lk7OPUwlA_TO3E9bA8vx7UTz1wBA_TtQ');

insert into user_mail (verify_mail, mail_code, user_login_id) values
(true, null, 2);

insert into user_profile (name, user_login_id) values
('root', 2);

insert into user_role (user_login_id, role_id) values
(2, 4);
