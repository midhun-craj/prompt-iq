# Building the .jar file using maven
FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package -DskipTests

# RUN chmod 755 target/*.jar

#creating the image
FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

RUN useradd -m appUser

WORKDIR /appUser

COPY --from=builder --chown=appUser:appUser /app/target/*.jar app.jar

RUN chmod +x app.jar

USER appUser

ENTRYPOINT [ "java", "-jar", "/appUser/app.jar" ]