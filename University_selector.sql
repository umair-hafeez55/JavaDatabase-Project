create database university_selector;
use university_selector;

CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    father_name VARCHAR(100),
    age INT,
    gender CHAR(1),
    cgpa DECIMAL(3,2),
    starting_year INT,
    ending_year INT,
    division VARCHAR(20),
    gat_score DECIMAL(5,2),
    program_level VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE Universities (
    university_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) UNIQUE NOT NULL,
    min_cgpa DECIMAL(3,2) NOT NULL,
    passing_year_start INT NOT NULL,
    passing_year_end INT NOT NULL,
    required_division VARCHAR(20) NOT NULL,
    required_gat DECIMAL(5,2) NOT NULL,
    program_level VARCHAR(50) NOT NULL,
    description TEXT,
    website_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE University_URLs (
    url_id INT PRIMARY KEY AUTO_INCREMENT,
    university_id INT NOT NULL,
    url_type ENUM('eligibility', 'application', 'website') NOT NULL,
    url VARCHAR(255) NOT NULL,
    FOREIGN KEY (university_id) REFERENCES Universities(university_id)
);

CREATE TABLE GAT_Questions (
    question_id INT PRIMARY KEY AUTO_INCREMENT,
    question_text TEXT NOT NULL,
    option_a VARCHAR(255) NOT NULL,
    option_b VARCHAR(255) NOT NULL,
    option_c VARCHAR(255) NOT NULL,
    option_d VARCHAR(255) NOT NULL,
    correct_answer CHAR(1) NOT NULL,
    category VARCHAR(50),
    difficulty_level INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE User_Quiz_Results (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    score INT NOT NULL,
    total_questions INT NOT NULL,
    percentage DECIMAL(5,2) NOT NULL,
    quiz_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
show tables;

select * from  User_Quiz_Results;
