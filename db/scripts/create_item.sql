create TABLE IF NOT EXISTS items (
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    created TIMESTAMP not null default current_timestamp,
    done BOOLEAN default false
);