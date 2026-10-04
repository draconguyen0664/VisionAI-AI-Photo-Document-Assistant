package com.visionai.ocr;

import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component @Profile("dev")
public final class DevOcrProvider implements OcrProvider {
    public Result recognize(String objectKey) { var text = "Development OCR output for " + objectKey; return new Result(text, "en", 1.0, List.of(new Block(1, text, 1.0)), 1); }
}
