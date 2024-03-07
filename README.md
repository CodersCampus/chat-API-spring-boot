# Spring Boot Chat Application

This is a chat application built using Spring Boot, integrated with Spring Security for JWT token authentication and MongoDB repository for data storage. It provides WebSocket support for real-time communication between users, enabling seamless chatting functionality.

## Features

- **JWT Token Authentication**: Implements Spring Security with JWT tokens for secure user authentication and authorization.
- **Real-time Chatting**: Utilizes WebSocket for real-time communication between users, enabling instant messaging.
- **MongoDB Integration**: Integrates MongoDB repository for storing chat messages and user information.
- **React UI Integration**: Integrated with a React frontend application for the user interface, providing a seamless chat experience.
- **Dependency Highlights**:
  - `jackson-datatype-jsr310`: For serializing/deserializing Java objects to/from JSON format, particularly for handling Java 8 date/time objects.
  - `spring-boot-starter-security`: Starter for using Spring Security for authentication and authorization.
  - `spring-boot-starter-websocket`: Starter for using WebSocket support in Spring Boot applications.
  - `jjwt-api`, `jjwt-jackson`, `jjwt-impl`: For JWT token generation, parsing, and validation.
  - `spring-boot-starter-data-mongodb`: Starter for using MongoDB as the data store in Spring Boot applications.
  - `spring-boot-starter-test`, `spring-security-test`: Dependencies for testing Spring Boot and Spring Security components.

## Prerequisites

Before running this project locally, ensure you have the following installed:

- Java Development Kit (JDK)
- Maven
- MongoDB

## Getting Started

1. Clone the repository:
   ### Using SSH

   ```bash
   git clone git@github.com:CodersCampus/chat-API-spring-boot.git
   ```

   OR
   ### Using HTTPS

     ```bash
   git clone https://github.com/CodersCampus/chat-API-spring-boot.git
   ```

2.  Navigate to the project directory:
```bash
cd chat-API-spring-boot

```

3. Build the project:
```bash
  mvn clean instal
```
OR
Use your IDEA

4. Run the project
It will run on `localhost:8080`

## Usage

  Access the application through the provided URL and register/login to start chatting.
  Utilize the WebSocket-powered chat interface to exchange messages with other users in real-time.

## Integration with React UI

The chat application is integrated with a React frontend application for the user interface. Ensure the React frontend is configured to communicate with the Spring Boot backend using appropriate API endpoints.
