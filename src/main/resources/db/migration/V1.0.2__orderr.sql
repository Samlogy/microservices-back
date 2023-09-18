CREATE TABLE IF NOT EXISTS orderr (
    id SERIAL PRIMARY KEY,
    status VARCHAR(255),
    total_price REAL,
    order_date DATE
);
