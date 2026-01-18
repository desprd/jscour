package com.github.desprd.jscour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Builder for the {@code Validator} class. Provides methods for including
 * validation rules into the final {@code Validator} object.
 * Note that the order in which these methods are called matters:
 * the rule added first will be validated before the others.
 *
 * @author Ilja Kanstanczuk
 */

public final class ValidatorBuilder implements ValidationModeStep, ValidationRulesStep{

    private final List<ValidationRule> rules = new ArrayList<>();
    private ValidationMode validationMode;
    private boolean toCompute = false;

    private ValidatorBuilder(){}

    public static ValidationModeStep builder() {
        return new ValidatorBuilder();
    }

    @Override
    public ValidationRulesStep ascii128() {
        validationMode = ValidationMode.ASCII128;
        return this;
    }

    @Override
    public ValidationRulesStep latin1() {
        validationMode = ValidationMode.LATIN1;
        return this;
    }

    @Override
    public ValidationRulesStep unicode() {
        validationMode = ValidationMode.UNICODE;
        return this;
    }

    @Override
    public ValidationRulesStep blockOffensiveWords() {
        rules.add(new BlockOffensiveWordsRule(
                ValidationFailureReason.OFFENSIVE_WORD
        ));
        return this;
    }

    @Override
    public ValidationRulesStep englishOnly() {
        rules.add(new EnglishOnlyRule(
                ValidationFailureReason.NON_ENGLISH_CHARACTERS
        ));
        return this;
    }

    @Override
    public ValidationRulesStep charactersLimit(int limit) {
        rules.add(new CharactersLimitRule(
                ValidationFailureReason.TOO_LONG,
                limit
        ));
        return this;
    }

    @Override
    public Validator build() {
        Objects.requireNonNull(validationMode, "Validation mode must be selected");
        return new Validator(rules, validationMode, toCompute);
    }

}