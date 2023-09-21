create table if not exists customer (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(255),
    phone INT
);