FROM gradle:8.3.0-jdk17-alpine

WORKDIR /app
COPY ../build/libs/app-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]

