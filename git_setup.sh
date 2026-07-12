#!/bin/bash

# Navigate to project directory
cd "/home/chaninduimanjith/Student Attendance Management System"

# Initialize Git
git init

# Configure Git temporarily if not set
git config user.name "Chanindu Imanjith"
git config user.email "chanindu@github.com"

# Set remote origin
git remote remove origin 2>/dev/null
git remote add origin https://github.com/ChaninduImanjith/student-attendance-management-system.git

# Create .gitignore and initial commit on main
echo "target/" > .gitignore
echo "*.class" >> .gitignore
echo "*.jar" >> .gitignore
echo ".idea/" >> .gitignore
echo "*.iml" >> .gitignore
git add .gitignore
git commit -m "Initial commit: Add gitignore"
git branch -M main

# 1. Initial Setup
git checkout -b feature/project-setup
git add pom.xml README.md TESTING.md sams_schema.sql src/main/resources/hibernate.cfg.xml
git commit -m "Setup Maven project, database schema, and project documentation"
git checkout main
git merge feature/project-setup

# 2. Database Entities
git checkout -b feature/entities
git add src/main/java/com/sams/entity/
git commit -m "Create Hibernate entity models based on database schema"
git checkout main
git merge feature/entities

# 3. DAO Layer
git checkout -b feature/dao-layer
git add src/main/java/com/sams/dao/ src/main/java/com/sams/util/HibernateUtil.java
git commit -m "Implement Data Access Object (DAO) layer for database interactions"
git checkout main
git merge feature/dao-layer

# 4. Service Layer
git checkout -b feature/service-layer
git add src/main/java/com/sams/service/
git commit -m "Implement Service layer for business logic abstraction"
git checkout main
git merge feature/service-layer

# 5. UI Base & Login
git checkout -b feature/login-system
git add src/main/java/com/sams/Main.java src/main/java/module-info.java src/main/java/com/sams/util/ViewUtil.java src/main/java/com/sams/controller/LoginController.java src/main/java/com/sams/controller/AdminDashboardController.java src/main/java/com/sams/controller/LecturerDashboardController.java src/main/resources/view/login.fxml src/main/resources/view/admin_dashboard.fxml src/main/resources/view/lecturer_dashboard.fxml
git commit -m "Implement JavaFX UI base, login authentication, and role-based dashboards"
git checkout main
git merge feature/login-system

# 6. Course CRUD
git checkout -b feature/course-crud
git add src/main/java/com/sams/controller/CourseManagementController.java src/main/resources/view/course_management.fxml
git commit -m "Implement Course Management CRUD functionality"
git checkout main
git merge feature/course-crud

# 7. Student CRUD
git checkout -b feature/student-crud
git add src/main/java/com/sams/controller/StudentManagementController.java src/main/resources/view/student_management.fxml
git commit -m "Implement Student Management CRUD functionality"
git checkout main
git merge feature/student-crud

# 8. Lecturer CRUD
git checkout -b feature/lecturer-crud
git add src/main/java/com/sams/controller/LecturerManagementController.java src/main/resources/view/lecturer_management.fxml
git commit -m "Implement Lecturer Management CRUD functionality"
git checkout main
git merge feature/lecturer-crud

# 9. Class Scheduling
git checkout -b feature/class-scheduling
git add src/main/java/com/sams/controller/ClassManagementController.java src/main/resources/view/class_management.fxml
git commit -m "Implement Class Schedule Management"
git checkout main
git merge feature/class-scheduling

# 10. Attendance Management
git checkout -b feature/attendance-tracking
git add src/main/java/com/sams/controller/MarkAttendanceController.java src/main/resources/view/mark_attendance.fxml
git commit -m "Implement Attendance marking functionality for Lecturers"
git checkout main
git merge feature/attendance-tracking

# 11. Attendance Reports
git checkout -b feature/attendance-reports
git add src/main/java/com/sams/controller/AttendanceReportsController.java src/main/resources/view/attendance_reports.fxml
git commit -m "Implement Attendance Report generation with filtering"
git checkout main
git merge feature/attendance-reports

# Add any remaining stray files just in case
git checkout -b feature/final-polish
git add .
git commit -m "Final polish and minor updates"
git checkout main
git merge feature/final-polish

# Finally, push the main branch and all feature branches to GitHub
git push -u origin main
git push --all origin

echo "All branches and commits successfully pushed to GitHub!"
