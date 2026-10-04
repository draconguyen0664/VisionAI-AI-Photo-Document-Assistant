package com.visionai.ai;

import java.util.List;

public interface AiProvider {
    Analysis analyze(AnalysisRequest request);
    record AnalysisRequest(String objectKey, String extractedText, String language) {}
    record Analysis(String summary, List<String> keyPoints, List<String> tags, double confidence) {}
}
