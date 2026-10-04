package com.visionai.document.application;

public final class DocumentAccessDenied extends RuntimeException {
    public DocumentAccessDenied() { super("You do not have access to this document"); }
}
