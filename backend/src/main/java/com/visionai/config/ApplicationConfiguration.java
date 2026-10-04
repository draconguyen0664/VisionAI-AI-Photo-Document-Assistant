package com.visionai.config;

import com.visionai.document.application.DocumentApplicationService;
import com.visionai.document.application.DocumentRepository;
import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean Clock clock() { return Clock.systemUTC(); }
    @Bean DocumentApplicationService documentService(DocumentRepository repository, Clock clock) { return new DocumentApplicationService(repository, clock); }
}
