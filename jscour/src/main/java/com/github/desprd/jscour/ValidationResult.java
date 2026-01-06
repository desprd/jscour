package com.github.desprd.jscour;

import org.jspecify.annotations.Nullable;

/**
 * Represents the result of a validation performed by {@link Validator}.
 * Note that {@code reason} is always {@code null} if {@code isValid} is {@code true}.
 *
 * @author Ilja Kanstanczuk
 */
public final class ValidationResult {

    private final String originalWord;
    private final boolean isValid;
    @Nullable
    private final ValidationFailureReason reason;

    private ValidationResult(String originalWord, boolean isValid, ValidationFailureReason reason) {
        this.originalWord = originalWord;
        this.isValid = isValid;
        this.reason = reason;
    }

    public static ValidationResult success(String word) {
        return new ValidationResult(word, true, null);
    }

    public static ValidationResult failure(String word, ValidationFailureReason reason) {
        return new ValidationResult(word, false, reason);
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public boolean isValid() {
        return isValid;
    }

    @Nullable
    public ValidationFailureReason getReason() {
        return reason;
    }
}
