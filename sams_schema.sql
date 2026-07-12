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

-- ============================================================
-- Sample Data (Sri Lankan)
-- ============================================================

-- Users
INSERT INTO User (username, password, role) VALUES 
('admin', 'admin123', 'Admin'),
('lecturer_perera', 'pass123', 'Lecturer'),
('lecturer_silva', 'pass123', 'Lecturer'),
('lecturer_fernando', 'pass123', 'Lecturer');

-- Courses
INSERT INTO Course (course_code, course_name, description) VALUES 
('CS101', 'Computer Science', 'BSc (Hons) in Computer Science'),
('IT201', 'Information Technology', 'BSc (Hons) in Information Technology'),
('SE301', 'Software Engineering', 'BSc (Hons) in Software Engineering');

-- Subjects
INSERT INTO Subject (subject_code, subject_name, course_id) VALUES 
('CS-PRG1', 'Programming Fundamentals', 1),
('CS-DS1',  'Data Structures & Algorithms', 1),
('IT-NET1', 'Computer Networks', 2),
('IT-DB1',  'Database Management Systems', 2),
('SE-SWD1', 'Software Design Patterns', 3),
('SE-AGI1', 'Agile Methodologies', 3);

-- Students
INSERT INTO Student (registration_number, name, email, phone, course_id) VALUES 
('CS2024001', 'Kavindu Lakshan Perera',    'kavindu.perera@student.ac.lk',   '0771234561', 1),
('CS2024002', 'Sanduni Madhavi Dissanayake','sanduni.d@student.ac.lk',        '0712345678', 1),
('CS2024003', 'Tharindu Hasantha Silva',    'tharindu.silva@student.ac.lk',   '0781234563', 1),
('CS2024004', 'Nimesha Kaushalya Fernando', 'nimesha.fernando@student.ac.lk', '0751234564', 1),
('IT2024001', 'Dineth Rajapaksha',          'dineth.r@student.ac.lk',         '0761234565', 2),
('IT2024002', 'Piyumi Dilhara Jayasinghe',  'piyumi.j@student.ac.lk',         '0771234566', 2),
('IT2024003', 'Sachith Maduranga Bandara',  'sachith.b@student.ac.lk',        '0711234567', 2),
('SE2024001', 'Dilshan Pradeep Ranasinghe', 'dilshan.r@student.ac.lk',        '0721234568', 3),
('SE2024002', 'Anushka Nethmi Wickramasinghe','anushka.w@student.ac.lk',      '0781234569', 3),
('SE2024003', 'Thisara Maduwantha Gamage',  'thisara.g@student.ac.lk',        '0751234570', 3);

-- Lecturers
INSERT INTO Lecturer (employee_id, name, email, phone, user_id) VALUES 
('EMP001', 'Dr. Chaminda Perera',   'c.perera@university.ac.lk',   '0112345671', 2),
('EMP002', 'Ms. Rashmi Silva',      'r.silva@university.ac.lk',    '0112345672', 3),
('EMP003', 'Mr. Lasantha Fernando', 'l.fernando@university.ac.lk', '0112345673', 4);

-- Lecturer-Subject Assignments
INSERT INTO Lecturer_Subject (lecturer_id, subject_id) VALUES 
(1, 1), -- Dr. Perera -> Programming Fundamentals
(1, 2), -- Dr. Perera -> Data Structures
(2, 3), -- Ms. Silva  -> Computer Networks
(2, 4), -- Ms. Silva  -> Database Management
(3, 5), -- Mr. Fernando -> Software Design Patterns
(3, 6); -- Mr. Fernando -> Agile Methodologies

