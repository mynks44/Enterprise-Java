CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(20)
);

CREATE TABLE threads (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    created_by VARCHAR(50),
    created_at TIMESTAMP
);

CREATE TABLE posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    thread_id INT,
    content TEXT,
    posted_by VARCHAR(50),
    posted_at TIMESTAMP,
    FOREIGN KEY (thread_id) REFERENCES threads(id)
);
