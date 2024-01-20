FROM maven:3.8.3-openjdk-17 AS buildx
WORKDIR /app
COPY . .
RUN mvn clean install

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=buildx app/target/estate-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "estate-0.0.1-SNAPSHOT.jar"]
