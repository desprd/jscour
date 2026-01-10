package com.github.desprd.jscour;

import java.util.Optional;

final class EnglishOnlyRule extends ValidationRule{

    EnglishOnlyRule(ValidationFailureReason failureMessage) {
        super(failureMessage);
    }

    @Override
    boolean isValid(ValidationRuleContext context) {
        return true;
    }
}
