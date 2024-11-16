FROM openjdk:17-jdk-slim AS build

WORKDIR /app

COPY . .

RUN ./gradlew build --no-daemon

RUN find /app/build/libs -name "*.jar" -exec mv {} /app/app.jar \;

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /app/app.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
