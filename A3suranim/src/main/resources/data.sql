INSERT INTO users (username, password, role) VALUES
('admin', '$2a$12$f21VkZoWhsKLnhwY80MxauQa4uzxFzATbDBF05dmP1YtNCwHVeR9y', 'ROLE_ADMIN'),
('user1', '$2a$12$PW4oxNmjDolz5m3OMWCB/.fdLUqHppwPwVF3WP6nW7mK3UrqI2b.W', 'ROLE_USER');

INSERT INTO threads (title, created_by, created_at) VALUES
('Welcome to the Forum!', 'admin', CURRENT_TIMESTAMP);

INSERT INTO posts (thread_id, content, posted_by, posted_at) VALUES
(1, 'This is the first welcome post!', 'admin', CURRENT_TIMESTAMP);
