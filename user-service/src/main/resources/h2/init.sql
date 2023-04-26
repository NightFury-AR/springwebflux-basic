create table account_user (
    id bigint auto_increment,
    name varchar(50),
    email varchar(50),
    balance int,
    primary key (id)
);

create table user_transaction (
    id bigint auto_increment,
    user_id bigint,
    amount int,
    transaction_date timestamp,
    foreign key (user_id) references account_user (id) on delete cascade
);

insert into account_user
    (name,email,balance)
    values
    ('sam', 'sam@service.com',1000),
    ('mike', 'mike@service.com', 1200),
    ('jake', 'jake@service.com', 800),
    ('marshal','marshal@service.com', 2000);