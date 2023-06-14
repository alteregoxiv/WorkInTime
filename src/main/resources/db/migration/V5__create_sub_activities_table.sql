CREATE TABLE sub_activities(
    id SERIAL PRIMARY KEY,
    sub_activity_name VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activity_id INT,
    FOREIGN KEY (activity_id) REFERENCES activities(id)
);