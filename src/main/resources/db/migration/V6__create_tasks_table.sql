CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    task_name VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    finished_at TIMESTAMP,
    start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_time TIMESTAMP,
    total_time TIME,
    product_id INT,
    activity_id INT,
    sub_activity_id INT,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (activity_id) REFERENCES activities(id),
    FOREIGN KEY (sub_activity_id) REFERENCES sub_activities(id)
);