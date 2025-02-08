
# Java Spring Boot JPA vs JDBC POC

This project is a Proof of Concept (POC) designed to help you understand and compare the usage of Java Spring Boot with JPA (Java Persistence API) and JDBC (Java Database Connectivity). The project demonstrates how both approaches interact with a relational database (e.g., AWS RDS) and provides guidance on setting up each implementation.

## Repository Description
This repository showcases a comprehensive comparison between JPA and JDBC using Spring Boot. It aims to help developers understand when to use JPA for rapid development and abstraction versus JDBC for performance-critical tasks. The project includes practical examples, setup instructions, and API endpoints for both implementations.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Project Setup](#project-setup)
- [Database Configuration](#database-configuration)
- [JPA Implementation](#jpa-implementation)
- [JDBC Implementation](#jdbc-implementation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Comparison: JPA vs JDBC](#comparison-jpa-vs-jdbc)
- [Conclusion](#conclusion)

## Prerequisites

- Java 17 or later
- Maven 3.6+
- AWS RDS (optional for cloud deployment)
- Docker (optional for local database setup)

## Project Setup

1. Clone this repository:

   ```bash
   git clone https://github.com/NetKanet/springboot-jpa-jdbc-poc.git
   cd springboot-jpa-jdbc-poc
   ```

2. Create a `.env` file for database credentials:

   ```env
   DB_URL=jdbc:mysql://localhost:3306/demo_db
   DB_USERNAME=root
   DB_PASSWORD=yourpassword
   ```

3. Build the project:

   ```bash
   mvn clean install
   ```

## Database Configuration

### Local Database Setup (Optional)

To run a local MySQL instance using Docker:

```bash
docker run --name mysql-db -e MYSQL_ROOT_PASSWORD=yourpassword -e MYSQL_DATABASE=demo_db -p 3306:3306 -d mysql:latest
```

### AWS RDS Configuration

If you prefer to use AWS RDS:

1. Create an RDS instance with MySQL.
2. Update the `.env` file with your RDS endpoint and credentials.

## JPA Implementation

1. JPA provides an abstraction layer over database operations.
2. Define an entity class:
   ```java
   @Entity
   public class User {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       private String name;
       private String email;

       // Getters and Setters
   }
   ```
3. Create a repository:
   ```java
   public interface UserRepository extends JpaRepository<User, Long> {
   }
   ```

## JDBC Implementation

1. JDBC requires manual SQL query handling.
2. Create a DAO class:
   ```java
   @Repository
   public class UserDAO {
       private final JdbcTemplate jdbcTemplate;

       public UserDAO(JdbcTemplate jdbcTemplate) {
           this.jdbcTemplate = jdbcTemplate;
       }

       public List<User> getAllUsers() {
           return jdbcTemplate.query("SELECT * FROM users",
               (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"), rs.getString("email")));
       }
   }
   ```

## Running the Application

To start the application:

```bash
mvn spring-boot:run
```

## API Endpoints

- **GET /users** - Retrieve all users (implemented for both JPA and JDBC).
- **POST /users** - Create a new user.

## Comparison: JPA vs JDBC

| Feature         | JPA              | JDBC                |
| --------------- | ---------------- | ------------------- |
| Abstraction     | High             | Low                 |
| Performance     | Slightly Slower  | Faster (direct SQL) |
| Query Handling  | Automatic (JPQL) | Manual (SQL)        |
| Code Complexity | Lower            | Higher              |

### Key Takeaways

- **JPA:** Best for quick development and readable code.
- **JDBC:** Best for performance-critical applications where fine control over SQL is required.

## Conclusion

This project demonstrates the practical differences between JPA and JDBC in Spring Boot. Understanding these differences will help you choose the right approach for your specific application needs.

## Future Improvements

- Add unit tests for both JPA and JDBC implementations.
- Integrate AWS services (e.g., S3, SNS, and SQS).
- Enhance error handling and validation.
