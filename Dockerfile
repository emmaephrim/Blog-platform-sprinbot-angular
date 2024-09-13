# Use a base image that has Java runtime installed
FROM openjdk:21-jdk

# Set the working directory inside the container
# WORKDIR /app
VOLUME /tmp

# Copy the packaged JAR file from your target directory to the container
COPY target/*.jar app.jar

# Expose the port your Spring Boot application runs on
# EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
