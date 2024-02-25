# E-Learniverse Java Spring Boot Project
A Java Spring Boot Backend application for E-Learniverse. This is a Self-Learning Project to document all my Personal leanrings in Java Spring Boot Framework.

## Installation guide
Please follow standard **Java Spring** installation guide.

### secret.properties
First create a  **secret.properties** file in `src/main/resources` folder & give following key-value pairs.
```shell script
# Postgres Database Secret Files
database-url: jdbc:postgresql://localhost:5432/db_name
database-username: ***
database-password: ***

# Email Service Secret Files
email-address: demo@gmail.com
email-app-password: ***

# RabbitMQ Secret Files
rabbitmq-host-url: 192.168.0.1
rabbitmq-username: guest
rabbitmq-password: guest
```