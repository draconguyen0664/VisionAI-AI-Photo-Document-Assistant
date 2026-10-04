FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . .
RUN mvn -B -ntp package
FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S visionai && adduser -S visionai -G visionai
USER visionai
COPY --from=build /workspace/target/visionai-backend-0.1.0.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
