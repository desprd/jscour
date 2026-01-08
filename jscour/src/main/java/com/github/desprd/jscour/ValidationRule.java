package com.github.desprd.jscour;

abstract class ValidationRule {

    private final ValidationFailureReason failureMessage;

    ValidationRule(ValidationFailureReason failureMessage) {
        this.failureMessage = failureMessage;
    }

    abstract boolean isValid(String input);

    ValidationFailureReason getFailureMessage() {
        return failureMessage;
    }
}
