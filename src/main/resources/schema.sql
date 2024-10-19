CREATE Table if not exists "users" (
    id serial primary key,
    name varchar(255) not null,
    password varchar(255) not null
);

CREATE Table if not exists "emotions" (
    id serial primary key,
    name varchar(255) not null,
    emoji varchar(255) not null,
    emotion_date date not null,
    user_id integer not null,
    FOREIGN KEY(user_id) REFERENCES "users"(id)
);

CREATE Table if not exists "exercises" (
    id serial primary key,
    user_id integer not null,
    name varchar(255) not null,
    reps integer not null,
    weight integer not null,
    work_out_date date not null,
    FOREIGN KEY(user_id) REFERENCES "users"(id)
);