create table users(
id UUID primary KEY,
first_name varchar(255),
last_name varchar(255),
email varchar(255) not null unique,
phone_number varchar(255) not null unique,
password varchar(255) not null,
role varchar(255),
status varchar(255),
created_at timestamp,
updated_at timestamp
);