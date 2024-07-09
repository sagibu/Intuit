FROM openjdk:21-jdk-slim
WORKDIR /app
ENV PLAYERS_FILE player.csv
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]