# VisionAI

AI Photo & Document Assistant built as a Kotlin Multiplatform and Spring Boot portfolio monorepo.

Work in progress: backend metadata endpoints and in-memory development repository; Compose Home demo. Scanner and other destinations are placeholders. Flutter/admin and production Firebase, persistence, Gemini and storage integrations are incomplete.

## Run backend demo

Requires Java 21+ and Maven. From `backend`:

```powershell
mvn test
mvn spring-boot:run "-Dspring-boot.run.profiles=dev"
```

API: `http://127.0.0.1:8080/api/v1/documents`. Dev uses one fixed identity and in-memory data, binds only to localhost, and must not be exposed publicly. Data disappears on restart.

Optional infrastructure: start Docker Desktop and run `docker compose up -d` from the root. The dev backend does not use these services yet.

Android requires Gradle and SDK 35. No Gradle wrapper is checked in; Android/iOS builds are unverified. Flutter/admin contain documentation only.
