# Todo List Spring Boot Application

This Spring Boot application is designed to expose endpoints for managing todo lists. It allows users to save, retrieve, and create todo lists. The application features registration and login functionalities and supports three distinct roles.

## Features

- **Registration**: New users can register. During registration, users can opt for specific roles using special strings:
    - No string (or "null"): Assigns the `ROLE_USER`
    - "mod": Assigns the `ROLE_MODERATOR`

  > Note: `ROLE_ADMIN` should be assigned manually or through another mechanism to ensure higher security.

- **Login**: Users can log in using their credentials.
- **Todo Lists**: Once authenticated, users can:
    - Create new todo lists
    - Save individual todo items
    - Retrieve their todo lists

## Endpoint

1. `/register`: POST endpoint for user registration.
2. `/login`: POST endpoint for user login.
3. `/todo`: POST endpoint to create a new todo list.
4. `/todo/{id}`: GET endpoint to retrieve a specific todo list. PUT endpoint to update a specific todo list.

## Roles and Permissions

- **ROLE_USER**: Can create, view, and edit their own todo lists.
- **ROLE_MODERATOR**: Inherits all `ROLE_USER` capabilities and can also view everyone's todo lists.
- **ROLE_ADMIN**: Inherits all previous role capabilities and can additionally manage users (e.g., assign roles, delete users).

## Authentication

Authentication in this application is done using bearer tokens. Ensure you pass the bearer token in the Authorization header for routes that require authentication.

## Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/Kryo96/todolist
   
2. Before running, ensure that you override the default database configurations and password from application.properties by creating an application-local.properties file.

3. Start the application using Maven:
    ```bash 
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=local

4. The application should now be running and accessible at: http://localhost:8080/
5. Default data are inserted in data.sql, in case you modified the database creation behaviour or it simply doesn't automatically load the roles, just perform the queries manually

## API Documentation

Complete documentation of the endpoints is available via Swagger UI at: http://localhost:8080/swagger-ui.html
