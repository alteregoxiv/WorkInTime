CREATE TABLE users_activities (
    id SERIAL PRIMARY KEY,
    user_id INT,
    activity_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (activity_id) REFERENCES activities(id)
);