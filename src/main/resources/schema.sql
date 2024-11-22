CREATE Table if not exists "users" (
    id serial primary key,
    name varchar(255) not null,
    password varchar(255) not null,
    pfp varchar(255) not null
);

CREATE Table if not exists "emotions" (
    id serial primary key,
    fear integer not null,
    suprise integer not null,
    sadness integer not null,
    disgust integer not null,
    anger integer not null,
    anticipation integer not null,
    joy integer not null,
    trust integer not null,
    emotion_date date not null,
    user_id integer not null,
    FOREIGN KEY(user_id) REFERENCES "users"(id)
);

CREATE Table if not exists "exercises" (
    id serial primary key,
    user_id integer not null,
    name varchar(255) not null,
    minutes integer not null,
    reps integer not null,
    weight integer not null,
    work_out_date date not null,
    FOREIGN KEY(user_id) REFERENCES "users"(id)
);

CREATE TABLE if not exists "user_relations" (
    user1_id integer not null,
    user2_id integer not null,
    user2_accepted boolean not null DEFAULT FALSE,
    FOREIGN KEY(user1_id) REFERENCES "users"(id),
    FOREIGN KEY(user2_id) REFERENCES "users"(id),
    PRIMARY KEY (user1_id, user2_id)
);