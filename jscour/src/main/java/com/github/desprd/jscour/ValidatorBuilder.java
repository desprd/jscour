package com.github.desprd.jscour;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for the {@code Validator} class. Provides methods for including
 * validation rules into the final {@code Validator} object.
 * Note that the order in which these methods are called matters:
 * the rule added first will be validated before the others.
 *
 * @author Ilja Kanstanczuk
 */

public final class ValidatorBuilder {

    private final List<ValidationRule> rules = new ArrayList<>();

    public static ValidatorBuilder builder() {
        return new ValidatorBuilder();
    }

    public ValidatorBuilder blockOffensiveWords() {
        rules.add(new BlockOffensiveWordsRule(ValidationFailureReason.OFFENSIVE_WORD));
        return this;
    }

    public ValidatorBuilder englishOnly() {
        rules.add(new EnglishOnlyRule(ValidationFailureReason.NON_ENGLISH_CHARACTERS));
        return this;
    }

    public ValidatorBuilder charactersLimit(int limit) {
        rules.add(new CharactersLimitRule(ValidationFailureReason.TOO_LONG, limit));
        return this;
    }

    public Validator build() {
        return new Validator(rules);
    }

}