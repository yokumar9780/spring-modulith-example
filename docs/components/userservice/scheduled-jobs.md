# User Service Scheduled Jobs

## Daily Notification Job

- **Description**: Sends daily notifications to users based on their subscriptions.
- **Cron Expression**: `0 0 12 * * ?` (Executes daily at 12:00 PM)
- **Logic**: Fetches subscriptions that haven't been notified today and publishes events for notification.
