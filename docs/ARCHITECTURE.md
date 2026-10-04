# Architecture

KMP and Flutter call the same `/api/v1` Spring Boot API. Firebase establishes identity; the backend maps the verified issuer/subject to an internal user. PostgreSQL owns metadata, MinIO/S3 owns binaries, and Redis is limited to ephemeral rate-limit/cache state.

Document processing follows `QUEUED -> UPLOADING -> VALIDATING -> IMAGE_PROCESSING -> OCR_PROCESSING -> AI_ANALYSIS -> INDEXING -> READY`, with `FAILED` reachable from every active state. State transitions are validated in the domain and persisted transactionally.

Provider ports (`AiProvider`, `OcrProvider`, `ObjectStorage`) keep infrastructure replaceable. Development implementations are enabled only under `dev`; production configuration fails fast when credentials are missing.
