FROM maven:3.9.9-amazoncorretto-21
LABEL authors="thuan"
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests
COPY /target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]