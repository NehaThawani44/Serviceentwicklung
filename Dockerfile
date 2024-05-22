# Use a JDK 17 image from the official OpenJDK image repository on Docker Hub
FROM openjdk:17-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file from your Maven target folder into the Docker image
COPY target/task-service-0.0.1-SNAPSHOT.jar task-service.jar

# Command to run the application
CMD ["java", "-jar", "task-service.jar"]
