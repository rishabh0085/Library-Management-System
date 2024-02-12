# Library-Management-System

#About

This is a Spring Boot application for managing a library's operations. It provides functionalities such as issuing library cards, returning books, recording transactions, associateCardWithStudent, addBook, addAuthor, finding authors and books by ID, finding students by ID, generating new library cards, and updating phone numbers.

#Tech Stack

-Java
-Spring Boot
-Hibernate
-JPA
-Apache Tomcat
-MySQL
-Maven
-Postman (for testing APIs)
-Swagger (for API documentation)

#Setup

-Clone the repository:
bash
Copy code
git clone https://github.com/your-username/library-management-system.git


#Database setup:

-Install MySQL.
Create a new database named library_management_system.
Update application.properties with your MySQL username and password.

#Build and run the application:

bash
Copy code
cd library-management-system
mvn clean install
mvn spring-boot:run

#Access APIs:

Use Postman to test the APIs. Import the provided Library_Management_System.postman_collection.json file for convenience.

Access Swagger UI at http://localhost:8080/swagger-ui.html for detailed API documentation.

# 12+ APIs Writtern:

POST /issue-card: Issue a library card to a student.

POST /associateCardWithStudent.

POST /addBook.

POST /addAuthor.

POST /addStudent.

POST /return-book: Return a book to the library.

POST /record-transaction: Record a transaction (e.g., borrowing, returning).

GET /authors/{id}: Find an author by ID.

GET /books/{id}: Find a book by ID.

GET /students/{id}: Find a student by ID.

POST /generate-library-card: Generate a new library card for a student.

PUT /update-phone/{studentId}: Update a student's phone number.
