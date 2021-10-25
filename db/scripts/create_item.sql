create table if not exists items (
id serial primary key,
description text,
created date,
done boolean
);
