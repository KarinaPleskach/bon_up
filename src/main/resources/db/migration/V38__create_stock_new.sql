create table stock_new (
                            id bigserial primary key,
                            title character varying(255) not null,
                            description character varying(255) not null,
                            organization_new_id bigint not null,
                            category_id bigint not null,
                            date_from date not null,
                            date_to date not null,
                            photoId bigint not null,
                            constraint tasks_new_organizations_fk foreign key (organization_new_id) references organization_new (id),
                            constraint tasks_categories_fk foreign key (category_id) references category (id),
                            constraint mnew_organization_photo_to_photo_fk foreign key (photoId) references photo (id)
);