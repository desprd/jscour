package com.github.desprd.jscour;

import java.util.List;
import java.util.Optional;

/**
 * Validates a single word against a configured list of validation rules.
 * <p>
 * This validator first performs {@link InitialValidation#initValidate(String, ValidationMode, boolean)}
 * <p>
 * If the input passes the basic checks, rules are evaluated in the order they were added.
 * The first rule that fails determines the returned failure reason.
 *
 * @author Ilja Kanstanczuk
 */
public final class Validator{

    private final List<ValidationRule> rules;
    private final ValidationMode validationMode;
    private final boolean toCompute;

    Validator(List<ValidationRule> rules, ValidationMode validationMode, boolean toCompute) {
        this.rules = List.copyOf(rules);
        this.validationMode = validationMode;
        this.toCompute = toCompute;
    }

    public ValidationResult validate(String word) {
        final Optional<int[]> existingCharactersArray;
        try {
            existingCharactersArray = InitialValidation.initValidate(word, validationMode, toCompute);
        } catch (InitialValidationFailedException e) {
            return ValidationResult.failure(word, e.getFailureReason());
        }
        for (ValidationRule rule: rules) {
            if (!rule.isValid(new ValidationRuleContext(word, existingCharactersArray))) {
                return ValidationResult.failure(word, rule.getFailureMessage());
            }
        }
        return ValidationResult.success(word);
    }

}