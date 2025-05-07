# Spring Modulith Example

This project is a modular Spring Boot application that demonstrates the use of Spring Modulith for building modular applications. It includes multiple services such as User Service, Job Service, Notification Service, and Gateway Service, each with its own responsibilities.

## Features

- Modular architecture using Spring Modulith.
- API Gateway for routing and filtering requests.
- User management, job postings, and notification handling.
- Integration with PostgreSQL for data persistence.
- Scheduled jobs for daily notifications.
- Event-driven communication between services.

## Prerequisites

- Java 21
- Maven
- Docker and Docker Compose

## Quick Start

1. Clone the repository:
   ```bash
   git clone https://github.com/yokumar9780/spring-modulith-example.git
   cd spring-modulith-example
   ```
2. Start Docker services:
   ```bash
   docker-compose -f compose.yaml up -d
   ```
3. Build the project:
   ```bash
   ./mvnw clean install
   ```
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Access the application at `http://localhost:8080`.

## Documentation

For detailed documentation, visit the [MkDocs GitHub Pages](https://yokumar9780.github.io/spring-modulith-example/).
