FROM maven:3.8.5-openjdk-17 AS builder
LABEL authors="Boris Djotov"
WORKDIR /home/project

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=builder /home/project/target/*.jar SPAccreditation.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "SPAccreditation.jar"]