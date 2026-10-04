package com.visionai.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DocumentApiSmokeTest {
    @Autowired private TestRestTemplate client;

    @Test void createsAndReadsDocumentMetadata() {
        var created = client.postForEntity("/api/v1/documents", Map.of(
            "name", "Smoke test.pdf", "contentType", "application/pdf", "sizeBytes", 1024
        ), Map.class);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(created.getHeaders().getLocation()).isNotNull();
        var loaded = client.getForEntity(created.getHeaders().getLocation().toString(), Map.class);
        assertThat(loaded.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loaded.getBody()).containsEntry("name", "Smoke test.pdf").containsEntry("status", "QUEUED");
    }

    @Test void rejectsUnsupportedMediaType() {
        var result = client.postForEntity("/api/v1/documents", Map.of(
            "name", "program.exe", "contentType", "application/octet-stream", "sizeBytes", 1024
        ), Map.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(result.getBody()).containsEntry("code", "INVALID_REQUEST");
    }
}
