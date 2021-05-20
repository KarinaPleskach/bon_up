alter table accepted_user_profile_task
drop constraint accepted_user_profile_task_to_task_fk;
alter table accepted_user_profile_task
add constraint nt foreign key (task_id) references task_new (id);

alter table done_user_profile_task
    drop constraint done_user_profile_task_to_task_fk;
alter table done_user_profile_task
    add constraint ntd foreign key (task_id) references task_new (id);