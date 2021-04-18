create table stock_photo (
    photo_id bigint not null,
    stock_id bigint not null,
    constraint stock_photo_stock_id_photo_id_uq unique (photo_id, stock_id),
    constraint stock_photo_to_photo_fk foreign key (photo_id) references photo (id),
    constraint stock_photo_to_stock_fk foreign key (stock_id) references stock (id)
);