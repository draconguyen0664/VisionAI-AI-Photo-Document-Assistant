# Security model

- Production requests use Firebase bearer tokens verified by Spring Security. The verified subject is mapped to an internal UUID.
- Controllers never accept user IDs or ownership claims from request payloads or headers.
- Every resource query is scoped to the authenticated internal user; cross-user access returns 403/404 without leaking content.
- Gemini and storage credentials exist only in backend environment variables.
- Uploads are allow-listed by media type, capped at 25 MB, and require content inspection before processing.
- Logs may contain trace IDs and error codes, never tokens, secrets, OCR text or document binaries.
