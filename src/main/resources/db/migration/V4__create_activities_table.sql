CREATE TABLE activities (
    id SERIAL PRIMARY KEY,
    activity_name VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES products(id)
);