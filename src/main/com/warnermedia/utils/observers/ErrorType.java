package com.warnermedia.utils.observers;

public enum ErrorType {
    NONE("No errors display."),
    AFTER_SCHEDULED_EXPORT_EVENT("after scheduled export event started"),
    UNEXPECTED_STOP_EVENT("unexpected stop event detected and ignored in record state"),
    MISSING_ASSET("mediasource returned an error indicating that this asset could not be"),
    UNABLE_TO_ACCESS_MEDIASOURCE("mediasource appears to be unavailable"),
    OTHER_ERROR("Unknown error");

    private final String description;

    ErrorType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
