package com.visionai.document.domain;

public final class InvalidDocumentTransition extends RuntimeException {
    public InvalidDocumentTransition(ProcessingStatus from, ProcessingStatus to) {
        super("Cannot transition document from " + from + " to " + to);
    }
}
