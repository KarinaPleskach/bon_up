alter table task_new
    add column triggered_count int default 0;
alter table coupon_new
    add column triggered_count int default 0;