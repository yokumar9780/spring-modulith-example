# Environment Configuration

This section will guide you through configuring the environment for the project.

## Application Properties

The application uses the `application.yml` file for configuration. Below are some key properties:

- **Database Configuration**:

  ```yaml
  spring:
    datasource:
      url: jdbc:postgresql://localhost:5432/spring-modulith
      username: dev_user
      password: dev_password
  ```

- **RabbitMQ Configuration**:

  ```yaml
  spring:
    rabbitmq:
      host: localhost
      port: 5672
  ```

- **Server Configuration**:
  ```yaml
  server:
    port: 8080
  ```

## Environment Variables

You can override the default configurations using environment variables:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `SPRING_RABBITMQ_HOST`
- `SPRING_RABBITMQ_PORT`

## Profiles

The application supports multiple profiles (e.g., `dev`, `prod`). You can specify the active profile using:

```bash
-Dspring.profiles.active=dev
```
