create TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name varchar(200) unique,
    email varchar(200) unique,
    password varchar(200)
    );

create TABLE IF NOT EXISTS items (
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    created TIMESTAMP not null default current_timestamp,
    done BOOLEAN default false,
	user_id int references users(id)
);