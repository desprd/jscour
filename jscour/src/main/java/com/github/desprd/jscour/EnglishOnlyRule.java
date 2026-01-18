package com.github.desprd.jscour;

final class EnglishOnlyRule extends ValidationRule{

    EnglishOnlyRule(ValidationFailureReason failureMessage) {
        super(failureMessage);
    }

    @Override
    boolean isValid(ValidationRuleContext context) {
        if (context.validationMode() == ValidationMode.UNICODE) {
            return checkForUnicodeCharacters(context.input());
        } else {
            return checkForAsciiCharacters(context.input());
        }
    }

    private boolean checkForAsciiCharacters(String input) {
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if ((current >= 'A' && current <= 'Z') || (current >= 'a' && current <= 'z')) continue;
            return false;
        }
        return true;
    }

    private boolean checkForUnicodeCharacters(String input) {
        for (int i = 0; i < input.length();) {
            int cp = input.codePointAt(i);
            boolean isUpper = cp >= 'A' && cp <= 'Z';
            boolean isLower = cp >= 'a' && cp <= 'z';
            if (!isUpper && !isLower) return false;
            i+= Character.charCount(cp);
        }
        return true;
    }

}
