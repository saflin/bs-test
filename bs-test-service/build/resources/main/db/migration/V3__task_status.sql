alter table task add column status varchar(32) not null;
alter table task add constraint check_status check (status IN ('PENDING', 'DONE', 'FAILED'));
