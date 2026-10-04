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

Optional infrastructure: start Docker Desktop and run `docker compose up -d postgres redis` from the root. PostgreSQL listens on localhost:15432 and Redis on localhost:6379. The dev backend does not use these services yet.

## Android demo

Requires a JDK compatible with Gradle 8.11.1 and Android SDK 35. Set `ANDROID_HOME` to your SDK location, then:

```powershell
.\gradlew.bat :apps:kmp:composeApp:assembleDebug :apps:kmp:composeApp:testDebugUnitTest
adb install -r apps/kmp/composeApp/build/outputs/apk/debug/composeApp-debug.apk
adb shell am start -n com.visionai.app/.MainActivity
```

Android build and the HomePresenter unit test passed on Windows. Installing/running requires a connected Android device or emulator; emulator startup on the development machine was blocked by insufficient disk space. iOS requires macOS/Xcode and remains unverified. Flutter/admin contain documentation only.

## MinIO local source build

`docker compose up -d --build minio` builds the pinned upstream source release because the former registry image is unavailable. This is a local AGPL-3.0 dependency; upstream is archived and no longer maintained: https://github.com/minio/minio. The build was stopped when local disk space dropped below 3 GB and is not yet verified. Reserve additional disk capacity before building. API/console ports are localhost:9000/9001.

## Verification

- Backend: `mvn -B -ntp -f backend/pom.xml test` ? 4 tests pass, including HTTP startup/create/read and unsupported-type rejection.
- Android: debug APK and 1 presenter test pass.
- PostgreSQL `pg_isready`, Redis `PING`, and backend HTTP GET verified.
- Production integrations and end-to-end file upload/processing are still incomplete. Adding credentials alone will not complete them.
