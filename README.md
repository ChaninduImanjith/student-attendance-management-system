# Student Attendance Management System (SAMS)

## Project Overview
The Student Attendance Management System (SAMS) is a desktop application developed to facilitate tracking and reporting student attendance for educational institutions. This system helps administrative staff and lecturers manage classes, monitor attendance, and generate attendance-related reports. It features role-based access for both Administrators and Lecturers.

## Technologies Used
* **Java 17:** Core programming language.
* **JavaFX:** UI Framework for building the desktop screens.
* **Hibernate ORM:** Database connectivity and Object-Relational Mapping.
* **MySQL:** Relational database for persistent storage.
* **Maven:** Dependency and build management.
* **Layered Architecture:** Clear separation between Presentation Layer (JavaFX Controllers), Service Layer (Business Logic), and Data Access Layer (DAOs).

## Setup Instructions
1. **Database Setup:**
   - Open your MySQL Workbench or CLI.
   - Execute the provided `sams_schema.sql` script located in the root directory to create the database schema, tables, and insert sample data.
2. **Configuration:**
   - Open `src/main/resources/hibernate.cfg.xml`.
   - Find `<property name="connection.password">` and insert your local MySQL root password inside the tag.
3. **Execution:**
   - Open the project in your IDE (e.g., IntelliJ IDEA, Eclipse) as a Maven Project.
   - Wait for Maven to download the dependencies defined in `pom.xml`.
   - Run the application from the `com.sams.Main` class.

## Login Credentials
You can use the following sample credentials populated by the SQL script to access the system:

| Role | Username | Password |
| :--- | :--- | :--- |
| **Admin** | `admin` | `admin123` |
| **Lecturer** | `lecturer_perera` | `pass123` |
| **Lecturer** | `lecturer_silva` | `pass123` |
| **Lecturer** | `lecturer_fernando` | `pass123` |

## Code Structure Highlights
* `com.sams.entity`: Contains Hibernate entity classes mirroring database tables.
* `com.sams.dao`: Interfaces and implementations for database operations.
* `com.sams.service`: Business logic validating and bridging UI with the DAO layer.
* `com.sams.controller`: JavaFX controllers managing the `src/main/resources/view/` FXML files.


![Login Screen](<screenshots/Screenshot From 2026-07-12 13-12-56.png>)
![Admin Dashboard](<screenshots/Screenshot From 2026-07-12 13-18-05.png>)
![Student Management](<screenshots/Screenshot From 2026-07-12 13-22-04.png>)