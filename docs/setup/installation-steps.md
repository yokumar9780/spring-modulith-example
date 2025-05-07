# Installation Steps

This section will guide you through the installation steps for the project.

## Prerequisites

- **Java 21**: Ensure Java 21 is installed.
- **Maven**: Install Maven for build and dependency management.
- **Docker**: Install Docker and Docker Compose.

## Steps

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/your-repo/spring-modulith-example.git
   cd spring-modulith-example
   ```

2. **Build the Project**:

   ```bash
   ./mvnw clean install
   ```

3. **Start Docker Services**:

   ```bash
   docker-compose -f compose.yaml up -d
   ```

4. **Run the Application**:

   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the Application**:
   - The application will be available at `http://localhost:8080`.
