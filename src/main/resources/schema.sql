CREATE Table if not exists "users" (
    id serial primary key,
    name varchar(255) not null,
    password varchar(255) not null
);

CREATE Table if not exists "emotions" (
    id serial primary key,
    name varchar(255) not null,
    emoji varchar(255) not null,
    date date not null,
    user_id integer not null,
    FOREIGN KEY(user_id) REFERENCES "users"(id)
);

/*
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int user_id;
    private String name;
    private int reps;
    private int weight;
    @Temporal(TemporalType.DATE)
    private Date work_out_date;
*/

CREATE Table if not exists "exercises" (
    id serial primary key,
    user_id integer not null,
    name varchar(255) not null,
    reps integer not null,
    weight integer not null,
    work_out_date date not null,
    FOREIGN KEY(user_id) REFERENCES "users"(id)
);