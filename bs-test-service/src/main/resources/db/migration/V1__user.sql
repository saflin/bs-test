create table user (
    id varchar(36) not null,
    user_name varchar(50) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    create_date_time datetime not null,
    update_date_time datetime not null,
    primary key (id)
);