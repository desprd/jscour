package com.github.desprd.jscour;

import java.util.List;
import java.util.Objects;

/**
 * Validates a single word against a configured list of validation rules.
 * <p>
 * This validator first performs basic input checks and fails fast:
 * {@code null} or blank input results in {@link ValidationFailureReason#EMPTY_INPUT},
 * and any input containing a white space results in
 * {@link ValidationFailureReason#NOT_A_SINGLE_WORD}.
 * <p>
 * If the input passes the basic checks, rules are evaluated in the order they were added.
 * The first rule that fails determines the returned failure reason.
 *
 * @author Ilja Kanstanczuk
 */
public final class Validator{

    private final List<ValidationRule> rules;

    Validator(List<ValidationRule> rules) {
        this.rules = List.copyOf(rules);
    }

    public ValidationResult validate(String word) {
        if (Objects.isNull(word) || word.isBlank()) {
            return ValidationResult.failure(word, ValidationFailureReason.EMPTY_INPUT);
        }
        if (word.chars().anyMatch(Character::isWhitespace)) {
            return ValidationResult.failure(word, ValidationFailureReason.NOT_A_SINGLE_WORD);
        }
        for (ValidationRule rule: rules) {
            if (!rule.isValid(word)) {
                return ValidationResult.failure(word, rule.getFailureMessage());
            }
        }
        return ValidationResult.success(word);
    }

}