package com.github.desprd.jscour;

final class EnglishOnlyRule extends ValidationRule{

    EnglishOnlyRule(ValidationFailureReason failureMessage) {
        super(failureMessage);
    }

    @Override
    boolean isValid(String input) {
        return true;
    }
}
