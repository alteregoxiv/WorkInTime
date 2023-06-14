CREATE TABLE users_tasks (
    id SERIAL PRIMARY KEY,
    user_id INT,
    task_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (task_id) REFERENCES tasks(id)
);