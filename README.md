# E-Learniverse Java Spring Boot Project
A Java Spring Boot Backend application for E-Learniverse. This is a Self-Learning Project to document all my Personal leanrings in Java Spring Boot Framework.

## Installation guide
Please follow standard **Java Spring** installation guide.

### secret.properties
First create a  **secret.properties** file in `application/e-learniverse/src/main/resources/secret.properties` folder & give following key-value pairs.
For the reference you can see **sample-secret.properties** file in `application/e-learniverse/src/main/resources/sample-secret.properties`

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

## Running the Project on Linux

### Prerequisites
Before running the project, ensure you have the following installed:
- **Java 11** or higher
- **PostgreSQL** database (running on localhost:5432)
- **RabbitMQ** server (optional, if using message queue features)

### Step 1: Make Gradle Wrapper Executable
If this is your first time running the project, make the Gradle wrapper executable:
```shell script
chmod +x gradlew
```

### Step 2: Clean Build (Optional)
To clean any previous builds:
```shell script
./gradlew clean
```

### Step 3: Build the Project
Build the entire project including all modules:
```shell script
./gradlew build
```

This will:
- Compile all Java source code
- Run tests
- Generate a WAR file in `application/e-learniverse/build/libs/`

### Step 4: Run the Application
After building, run the Spring Boot application:
```shell script
java -jar application/e-learniverse/build/libs/e-learniverse##1.0.0-SNAPSHOT.war
```

Alternatively, you can build and run in one command:
```shell script
./gradlew build && java -jar application/e-learniverse/build/libs/e-learniverse##1.0.0-SNAPSHOT.war
```

### Step 5: Access the Application
Once the application starts successfully, you can access it at:
```
http://localhost:8080
```

(Check the console output for the actual port if different)

### Additional Gradle Commands

**Build specific module:**
```shell script
./gradlew :application:e-learniverse:build
```

**Run tests:**
```shell script
./gradlew test
```

**View all available tasks:**
```shell script
./gradlew tasks
```

**Build without running tests:**
```shell script
./gradlew build -x test
```

**View project dependencies:**
```shell script
./gradlew dependencies
```

### Troubleshooting

**Issue: Permission denied when running ./gradlew**
```shell script
chmod +x gradlew
```

**Issue: Database connection errors**
- Ensure PostgreSQL is running
- Verify database credentials in `secret.properties`
- Check database URL format: `jdbc:postgresql://localhost:5432/<DATABASE_NAME>`

**Issue: Port already in use**
- Change the port in `application.properties` or `application.yml`
- Or stop the application using the port

**Issue: Build fails**
- Clean the build: `./gradlew clean`
- Check Java version: `java -version` (should be 11+)
- Delete `.gradle` folder and retry