package com.visionai.ocr;

import java.util.List;

public interface OcrProvider {
    Result recognize(String objectKey);
    record Block(int page, String text, double confidence) {}
    record Result(String fullText, String language, double confidence, List<Block> blocks, int pages) {}
}
