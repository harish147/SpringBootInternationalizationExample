CREATE TABLE IF NOT EXISTS task (
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    task_name VARCHAR(100) NOT NULL unique,
    description VARCHAR(200),
    creation_date DATE,
    due_date DATE,
    status VARCHAR(20),
    priority_level_number INT,
    CONSTRAINT status_check CHECK (status IN ('Completed', 'Pending'))
);