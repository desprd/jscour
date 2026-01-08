package com.github.desprd.jscour;

final class BlockOffensiveWordsRule extends ValidationRule{

    BlockOffensiveWordsRule(ValidationFailureReason failureMessage) {
        super(failureMessage);
    }

    @Override
    boolean isValid(String input) {
        return true;
    }
}