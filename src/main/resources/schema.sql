CREATE Table if not exists "user" (
    id serial primary key,
    name varchar(255) not null,
    password varchar(255) not null
);