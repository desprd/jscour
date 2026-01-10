package com.github.desprd.jscour;

import java.util.Optional;

final class BlockOffensiveWordsRule extends ValidationRule{

    BlockOffensiveWordsRule(ValidationFailureReason failureMessage) {
        super(failureMessage);
    }

    @Override
    boolean isValid(ValidationRuleContext context) {
        return true;
    }
}