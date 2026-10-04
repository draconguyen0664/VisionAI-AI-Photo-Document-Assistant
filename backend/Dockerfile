FROM gradle:8.12-jdk21 AS build
WORKDIR /workspace
COPY . .
RUN gradle bootJar --no-daemon
FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S visionai && adduser -S visionai -G visionai
USER visionai
COPY --from=build /workspace/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
