# System Testing Document (TESTING.md)

This document outlines the testing procedures for the Student Attendance Management System (SAMS). 
As per the coursework requirements, manual functional testing of the UI and database integration is the primary validation strategy.

## Test Environment Setup
1. Execute the `sams_schema.sql` in MySQL to prepare the database schema and sample data.
2. Update `src/main/resources/hibernate.cfg.xml` with your local MySQL password.
3. Run the application via the `com.sams.Main` class.

## Test Cases

### 1. User Login
- **Action:** Enter `admin` / `admin123`. Click Login.
- **Expected Result:** Admin Dashboard opens.
- **Action:** Enter `lecturer_john` / `pass123`. Click Login.
- **Expected Result:** Lecturer Dashboard opens.
- **Action:** Enter invalid credentials.
- **Expected Result:** Error message is displayed.

### 2. Course Management (Admin)
- **Action:** Navigate to "Manage Courses".
- **Action:** Fill in Course Code, Name, Description and click "Add Course".
- **Expected Result:** New course appears in the TableView and is saved to the MySQL database.
- **Action:** Select a course, change details, click "Update Course".
- **Expected Result:** Course details update in the table and DB.
- **Action:** Select a course, click "Delete Course".
- **Expected Result:** Course is removed from the table and DB.

### 3. Student Management (Admin)
- **Action:** Navigate to "Manage Students".
- **Expected Result:** The Course dropdown successfully loads available courses.
- **Action:** Add a student with a valid course selected.
- **Expected Result:** Student appears in the table.

### 4. Lecturer Management (Admin)
- **Action:** Navigate to "Manage Lecturers".
- **Action:** Add, Update, and Delete a Lecturer.
- **Expected Result:** The Lecturer table and database reflect the changes accurately.

### 5. Class Management (Admin)
- **Action:** Navigate to "Class Schedule".
- **Action:** Select a Course, Subject, Lecturer, Date, and Time. Click "Schedule Class".
- **Expected Result:** The Class is added successfully to the Schedule table.

### 6. Attendance Management (Lecturer)
- **Action:** Login as Lecturer. Navigate to "Mark Attendance".
- **Action:** Select a Class from the dropdown and click "Load Students".
- **Expected Result:** The table populates with students enrolled in the class's respective course.
- **Action:** Mark students as PRESENT or ABSENT, add remarks, click "Save Attendance".
- **Expected Result:** Attendance records are successfully saved to the `Attendance` table in MySQL and a success popup appears.

### 7. Attendance Reports
- **Action:** Navigate to "Attendance Reports".
- **Action:** Select a Student or Course from the filter dropdowns and click "Generate Report".
- **Expected Result:** The table filters to show only the relevant attendance records.
