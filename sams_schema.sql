CREATE DATABASE IF NOT EXISTS sams_db;
USE sams_db;

CREATE TABLE IF NOT EXISTS User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'Lecturer') NOT NULL
);

CREATE TABLE IF NOT EXISTS Course (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_code VARCHAR(20) UNIQUE NOT NULL,
    course_name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS Subject (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_code VARCHAR(20) UNIQUE NOT NULL,
    subject_name VARCHAR(100) NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    registration_number VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    course_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS Lecturer (
    lecturer_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Lecturer_Subject (
    lecturer_id INT,
    subject_id INT,
    PRIMARY KEY (lecturer_id, subject_id),
    FOREIGN KEY (lecturer_id) REFERENCES Lecturer(lecturer_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Class_Schedule (
    class_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT NOT NULL,
    subject_id INT NOT NULL,
    lecturer_id INT NOT NULL,
    class_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON DELETE CASCADE,
    FOREIGN KEY (lecturer_id) REFERENCES Lecturer(lecturer_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Attendance (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    class_id INT NOT NULL,
    student_id INT NOT NULL,
    status ENUM('PRESENT', 'ABSENT', 'LATE', 'EXCUSED') NOT NULL,
    remarks VARCHAR(255),
    FOREIGN KEY (class_id) REFERENCES Class_Schedule(class_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE
);

-- Sample Data
INSERT INTO User (username, password, role) VALUES 
('admin', 'admin123', 'Admin'),
('lecturer_john', 'pass123', 'Lecturer');

INSERT INTO Course (course_code, course_name, description) VALUES 
('CS101', 'Computer Science', 'BSc in Computer Science');

INSERT INTO Subject (subject_code, subject_name, course_id) VALUES 
('PRG101', 'Programming Fundamentals', 1);

INSERT INTO Student (registration_number, name, email, phone, course_id) VALUES 
('REG001', 'Alice Smith', 'alice@example.com', '1234567890', 1);

INSERT INTO Lecturer (employee_id, name, email, phone, user_id) VALUES 
('EMP001', 'John Doe', 'john@example.com', '0987654321', 2);

INSERT INTO Lecturer_Subject (lecturer_id, subject_id) VALUES (1, 1);
