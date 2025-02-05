# Use an official Maven image as the build environment
FROM maven:3.8.4-openjdk-17-slim AS build
# Set the working directory inside the container
WORKDIR /app

# Copy your Maven project into the container
COPY . /app

# Run Maven to build the Spring Boot application
RUN mvn clean package -DskipTests

# Use OpenJDK 17 as the runtime environment
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the .jar file from the build stage into the runtime container
COPY --from=build /app/target/gateway-0.0.1-SNAPSHOT.jar /app/message-server-1.0.0.jar

# Expose the port that the application will run on
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/message-server-1.0.0.jar"]
