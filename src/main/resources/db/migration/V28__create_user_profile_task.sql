create table accepted_user_profile_task (
    user_profile_id bigint not null,
    task_id bigint not null,
    constraint accepted_user_profile_task_to_user_profile_fk foreign key (user_profile_id) references user_profile (id),
    constraint accepted_user_profile_task_to_task_fk foreign key (task_id) references task (id),
    constraint accepted_profiles_tasks_uq unique (user_profile_id, task_id)
);

create table rejected_user_profile_task (
    user_profile_id bigint not null,
    task_id bigint not null,
    constraint rejected_user_profile_task_to_user_profile_fk foreign key (user_profile_id) references user_profile (id),
    constraint rejected_user_profile_task_to_task_fk foreign key (task_id) references task (id),
    constraint rejected_profiles_tasks_uq unique (user_profile_id, task_id)
);

create table done_user_profile_task (
    user_profile_id bigint not null,
    task_id bigint not null,
    constraint done_user_profile_task_to_user_profile_fk foreign key (user_profile_id) references user_profile (id),
    constraint done_user_profile_task_to_task_fk foreign key (task_id) references task (id),
    constraint done_profiles_tasks_uq unique (user_profile_id, task_id)
);
