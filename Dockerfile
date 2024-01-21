# Use a Maven image to build the application
FROM maven:3.8-openjdk-17-slim AS builder

# Set the working directory in the container to the application's root directory
WORKDIR /app

# Copy pom.xml and source code into the container's working directory
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17-jre-slim

# Set the working directory in the container to the application's root directory
WORKDIR /app
COPY --from=builder /app/target/jira-excel-importer-0.0.1-SNAPSHOT.jar ./jira-excel-importer-0.0.1-SNAPSHOT.jar

# Run the application using Java
CMD ["java", "-jar", "jira-excel-importer-0.0.1-SNAPSHOT.jar"]