package com.visionai.ai;

import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component @Profile("dev")
public final class DevAiProvider implements AiProvider {
    public Analysis analyze(AnalysisRequest request) { return new Analysis("Local development analysis.", List.of("No external AI request was made"), List.of("development"), 1.0); }
}
