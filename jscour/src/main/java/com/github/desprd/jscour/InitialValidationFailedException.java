package com.github.desprd.jscour;

final class InitialValidationFailedException extends Exception{

    private final ValidationFailureReason failureReason;

    public InitialValidationFailedException(ValidationFailureReason failureReason) {
        this.failureReason = failureReason;
    }

    public ValidationFailureReason getFailureReason() {
        return failureReason;
    }
}