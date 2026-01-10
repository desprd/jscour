package com.github.desprd.jscour;

import java.util.Optional;

abstract class ValidationRule {

    private final ValidationFailureReason failureMessage;

    ValidationRule(ValidationFailureReason failureMessage) {
        this.failureMessage = failureMessage;
    }

    abstract boolean isValid(ValidationRuleContext context);

    ValidationFailureReason getFailureMessage() {
        return failureMessage;
    }
}
