FROM maven:eclipse-temurin AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package spring-boot:repackage -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/service-user-0.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
