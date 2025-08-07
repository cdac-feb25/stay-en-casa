
# Stage 1: Build the application using Maven
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the JAR file from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]





# Use OpenJDK base image
#FROM openjdk:17-jdk-slim

# Set working directory
#WORKDIR /app

# Copy the Spring Boot JAR into the image
#COPY build/libs/*.jar app.jar

# Run the application
#ENTRYPOINT ["java", "-jar", "app.jar"]