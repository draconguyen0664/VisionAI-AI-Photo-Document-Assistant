package com.visionai.document.api;

import com.visionai.auth.CurrentUser;
import com.visionai.document.application.DocumentApplicationService;
import com.visionai.document.domain.Document;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {
    private final DocumentApplicationService service;
    private final CurrentUser currentUser;
    public DocumentController(DocumentApplicationService service, CurrentUser currentUser) { this.service = service; this.currentUser = currentUser; }

    @PostMapping
    ResponseEntity<DocumentResponse> create(Authentication authentication, @Valid @RequestBody CreateDocumentRequest request) {
        var document = service.create(currentUser.requireInternalId(authentication), new DocumentApplicationService.CreateDocument(request.name(), request.contentType(), request.sizeBytes()));
        return ResponseEntity.created(URI.create("/api/v1/documents/" + document.id())).body(DocumentResponse.from(document));
    }
    @GetMapping List<DocumentResponse> list(Authentication authentication) { return service.list(currentUser.requireInternalId(authentication)).stream().map(DocumentResponse::from).toList(); }
    @GetMapping("/{id}") DocumentResponse get(Authentication authentication, @PathVariable UUID id) { return DocumentResponse.from(service.get(currentUser.requireInternalId(authentication), id)); }

    record CreateDocumentRequest(@NotBlank String name, @NotBlank String contentType, @Min(1) @Max(26_214_400) long sizeBytes) {}
    record DocumentResponse(UUID id, String name, String contentType, long sizeBytes, String type, String status, String createdAt) {
        static DocumentResponse from(Document value) { return new DocumentResponse(value.id(), value.name(), value.contentType(), value.sizeBytes(), value.type().name(), value.status().name(), value.createdAt().toString()); }
    }
}
