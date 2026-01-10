package com.github.desprd.jscour;

import java.util.Optional;

final class CharactersLimitRule extends ValidationRule{

    private final int limit;

    CharactersLimitRule(ValidationFailureReason failureMessage, int limit) {
        super(failureMessage);
        this.limit = limit;
    }

    @Override
    boolean isValid(ValidationRuleContext context) {
        return context.input().length() <= limit;
    }


}
