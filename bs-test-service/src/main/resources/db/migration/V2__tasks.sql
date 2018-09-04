create table task (
    id varchar(36) not null,
    name varchar(255) not null,
    description varchar(255) not null,
    date_time datetime not null,
    user_id varchar(36) not null,
    primary key (id),
    foreign key (user_id) references user (id) on delete cascade
);