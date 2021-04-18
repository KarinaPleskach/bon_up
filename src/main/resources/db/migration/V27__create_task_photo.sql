create table task_photo (
    photo_id bigint not null,
    task_id bigint not null,
    constraint task_photo_task_id_photo_id_uq unique (photo_id, task_id),
    constraint task_photo_to_photo_fk foreign key (photo_id) references photo (id),
    constraint task_photo_to_task_fk foreign key (task_id) references task (id)
);