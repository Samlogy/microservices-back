create table if not exists order_item (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    quantity INT,
    price REAL,
    orderr_id INTEGER,
    CONSTRAINT fk_orderr_order_item
            FOREIGN KEY(orderr_id)
            REFERENCES orderr(id)
);