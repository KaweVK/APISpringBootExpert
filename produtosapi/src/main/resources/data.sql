create table product (
    id bigint not null primary key,
    name varchar(100) not null,
    description text,
    price numeric(12, 2)
);