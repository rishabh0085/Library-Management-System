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

# 16+ APIs Writtern:

- Add Movie API : `@PostMapping("addMovie")`
- Update Movie Details API : `@PutMapping("updateMovie")`
- Most Liked Movie API : `@GetMapping("mostLikedMovie")`
- Delete Movie API : `@DeleteMapping("deleteMovie")`
- Add Show API : `@PostMapping("addShow")`
- Delete Show API : `@DeleteMapping("deleteShow")`
- Add Show Seats API : `@PostMapping("addShowSeats")`
- Get Shows on Given Date API : `@GetMapping("getShowsOnGivenDate")`
- Count of Booked Seats API : `@GetMapping("countOfBookedSeats")`
- Add Theater API : `@RequestMapping("addTheater")`
- Delete Theater API : `@DeleteMapping("deleteTheater")`
- Add Physical Theater Seats API : `@PostMapping("addTheaterSeats")`
- Get Gross Revenue of Movie : `@GetMapping("grossRevenueOfMovie")`
- Book Ticket API : `@PostMapping("bookTicket")`
- Download Movie Ticket API : `@GetMapping("viewTicket")` and more...

