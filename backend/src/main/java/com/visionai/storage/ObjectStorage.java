package com.visionai.storage;

import java.io.InputStream;
import java.net.URI;
import java.time.Duration;

public interface ObjectStorage {
    void put(String key, InputStream content, long size, String contentType);
    URI signedDownloadUrl(String key, Duration validity);
    void delete(String key);
}
