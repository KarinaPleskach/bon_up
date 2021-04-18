create table organization_photo (
    photo_id bigint not null,
    organization_id bigint not null,
    constraint organization_photo_photo_id_organization_id_uq unique (photo_id, organization_id),
    constraint organization_photo_to_photo_fk foreign key (photo_id) references photo (id),
    constraint organization_photo_to_organization_fk foreign key (organization_id) references organization (id)
);
