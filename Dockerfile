FROM openjdk:17-jdk-slim
ARG JAR_FILE=bin/leap-ahead-x-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} leap-ahead-x.jar
ENTRYPOINT ["java", "-jar", "leap-ahead-x.jar"]
