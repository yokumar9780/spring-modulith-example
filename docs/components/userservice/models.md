# User Service Models

## User

- **Fields**:
  - `id`: Long
  - `name`: String
  - `email`: String
  - `password`: String
  - `role`: String (e.g., "JobSeeker", "Employer")

## Subscription

- **Fields**:
  - `id`: Long
  - `userId`: Long
  - `jobType`: String
  - `location`: String
  - `minSalary`: Double
  - `lastNotificationDate`: LocalDate
