FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:resolve dependency:resolve-plugins
COPY src ./src
RUN mvn -B package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/bin/leap-ahead-x-0.0.1-SNAPSHOT.jar /app/leap-ahead-x.jar
EXPOSE 8080
CMD ["java", "-jar", "leap-ahead-x.jar"]
