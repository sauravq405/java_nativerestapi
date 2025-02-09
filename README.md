
# Jakarta RESTful Web Service with H2 In-Memory Database

Welcome to the project repository for a Jakarta RESTful Web Service integrated with an H2 in-memory database. 

This repository hosts a project where I am attempting to replicate the functionality of Spring Boot REST APIs using purely native Java capabilities, focusing on creating a similar RESTful service environment with Jakarta EE, Jersey, and Grizzly, while integrating an H2 in-memory database. This endeavor provides insights into how one might achieve similar results outside of the Spring ecosystem, leveraging standard Java libraries and frameworks.

Below you'll find details on how to set up, run, and interact with this application.

## Overview

This project demonstrates a simple RESTful service using Jakarta EE, Jersey, and Grizzly for server setup, along with Jackson for JSON processing. It also includes an in-memory H2 database for data storage and provides access to the H2 Console for database management.

## Getting Started

### Prerequisites

- Java 17 or later
- Maven
- A modern web browser for accessing the H2 Console

### Setup

1. **Clone the Repository**: 
   ```bash
   git clone [repo-url]
   cd [repo-name]
   ```

2. **Build the Project**:
   ```bash
   mvn clean package
   ```

3. **Run the Application**:
   ```bash
   java -jar target/java_nativerestapi-1.0-SNAPSHOT.jar
   ```

   This command will start both the RESTful service on `http://localhost:8090` and the H2 Console on `http://localhost:8082`.

### Endpoints

- **GET /api/hello**: Returns a plain text greeting.
- **GET /api/hellojson**: Returns a JSON response with a greeting message.
- **POST /api/postexample**: Accepts JSON data and returns a JSON response.

### H2 Database

The project uses H2 as an in-memory database for simplicity and speed. Here's how you can interact with it:

- **Database URL**: `jdbc:h2:mem:testdb`
- **User**: `sa`
- **Password**: `` (empty)

Upon starting the application, the following operations are performed:
- A `PERSONS` table is created with columns `ID` and `NAME`.
- Sample data is inserted into the `PERSONS` table.

### Accessing H2 Console

To manage or view the in-memory database:

- Open your web browser and navigate to `http://localhost:8082`.
- Use the provided database URL, user, and password to connect.

**Note**: Ensure no other applications are using port 8082. If you face issues accessing the console, check your local network settings or firewall configurations in India to ensure access is allowed.

### Logging

The application uses Log4j for logging. Logs are configured to provide debug-level information, which can be adjusted in the `log4j2.xml` file located in `src/main/resources`.

### Troubleshooting

- **Port Conflicts**: If you encounter issues with port 8082, ensure it's not in use by running `lsof -i :8082` on macOS/Linux or `netstat -an | findstr 8082` on Windows.
- **H2 Console Not Accessible**: Verify that the H2 server is started correctly by checking the application logs for a confirmation message.

## Contributions

Feel free to contribute to this project by forking the repository and submitting pull requests. If you have any questions or suggestions, please open an issue.

