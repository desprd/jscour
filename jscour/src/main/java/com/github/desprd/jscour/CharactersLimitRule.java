package com.github.desprd.jscour;

final class CharactersLimitRule extends ValidationRule{

    private final int limit;

    CharactersLimitRule(ValidationFailureReason failureMessage, int limit) {
        super(failureMessage);
        this.limit = limit;
    }

    @Override
    boolean isValid(String input) {
        return input.length() <= limit;
    }


}
