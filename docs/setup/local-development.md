# Local Development Setup

This section will guide you through setting up the project for local development.

## Prerequisites

- **Java 21**: Ensure you have Java 21 installed.
- **Maven**: Build and dependency management tool.
- **Docker and Docker Compose**: Required for running services like PostgreSQL and RabbitMQ.

## Steps

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/your-repo/spring-modulith-example.git
   cd spring-modulith-example
   ```

2. **Start Docker Services**:
   Use the provided `compose.yaml` file to start the required services:

   ```bash
   docker-compose -f compose.yaml up -d
   ```

   This will start:

   - **PostgreSQL**: Accessible on port `5433`.
   - **RabbitMQ**: Accessible on port `5672`.

3. **Build the Project**:
   Use Maven to build the project:

   ```bash
   ./mvnw clean install
   ```

4. **Run the Application**:
   Start the Spring Boot application:

   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the Application**:
   - The application will be available at `http://localhost:8080`.
   - API Gateway routes:
     - User Service: `http://localhost:8080/api/users`
     - Job Service: `http://localhost:8080/api/jobs`
     - Notification Service: `http://localhost:8080/api/notifications`

## Additional Notes

- **Database Configuration**:
  The application connects to PostgreSQL using the following credentials (defined in `application.yml`):

  - URL: `jdbc:postgresql://localhost:5432/spring-modulith`
  - Username: `dev_user`
  - Password: `dev_password`

- **Logging**:
  Logs are stored in the `logs/` directory.

- **Testing**:
  Use the following command to run tests:
  ```bash
  ./mvnw test
  ```
