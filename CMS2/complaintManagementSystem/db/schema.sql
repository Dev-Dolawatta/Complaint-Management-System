
CREATE DATABASE IF NOT EXISTS cms_db;
USE cms_db;


CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       role ENUM('EMPLOYEE', 'ADMIN') NOT NULL,
                       full_name VARCHAR(100) NOT NULL
);


CREATE TABLE complaints (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            title VARCHAR(100) NOT NULL,
                            description TEXT NOT NULL,
                            status ENUM('PENDING', 'IN_PROGRESS', 'RESOLVED') DEFAULT 'PENDING',
                            remarks TEXT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            user_id INT NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES users(id)
);

