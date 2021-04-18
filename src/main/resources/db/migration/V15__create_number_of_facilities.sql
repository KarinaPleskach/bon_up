create table number_of_facilities (
    id bigserial primary key,
    stocks int not null,
    heavy_tasks int not null,
    medium_tasks int not null,
    easy_tasks int not null,
    heavy_coupons int not null,
    medium_coupons int not null,
    easy_coupons int not null
);
