package com.github.desprd.jscour;

import java.util.ArrayList;
import java.util.List;

public final class ValidatorBuilder {

    private final List<ValidationRule> rules = new ArrayList<>();

    public static ValidatorBuilder builder() {
        return new ValidatorBuilder();
    }

    public ValidatorBuilder blockOffensiveWords() {
        rules.add(new BlockOffensiveWordsRule());
        return this;
    }

    public ValidatorBuilder englishOnly() {
        rules.add(new EnglishOnlyRule());
        return this;
    }

    public ValidatorBuilder charactersLimit(int limit) {
        rules.add(new CharactersLimitRule());
        return this;
    }

    public Validator build() {
        return new Validator(rules);
    }

}