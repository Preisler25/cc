# Use an official OpenJDK runtime as a parent image
FROM openjdk:21

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container (make sure to reference the correct file name here)
COPY target/crazy_counter-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port (replace with the actual port your app is listening on)
EXPOSE 4000

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]
