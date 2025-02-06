-- Create the Student table
CREATE TABLE IF NOT EXISTS Student (
    id BIGINT NOT NULL AUTO_INCREMENT,  -- Assuming `id` is a primary key that auto-increments
    name VARCHAR(255) NOT NULL,
    course VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Insert some sample data
INSERT INTO Student (name, course) VALUES ('John Doe', 'Computer Science');
INSERT INTO Student (name, course) VALUES ('Jane Smith', 'Mathematics');
INSERT INTO Student (name, course) VALUES ('Alice Johnson', 'Physics');
