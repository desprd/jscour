package com.github.desprd.jscour;

import java.util.Objects;
import java.util.Optional;

/**
 * Utility class that performs initial input validation for
 * {@link Validator#validate(String)} and compute how many times
 * each symbol occurs in the input if {@code toCompute} is {@code true}
 *
 * @author Ilja Kanstanczuk
 */
final class InitialValidation {
    static Optional<int[]> initValidate(
            String input,
            ValidationMode validationMode,
            boolean toCompute
    ) throws InitialValidationFailedException {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new InitialValidationFailedException(
                    ValidationFailureReason.EMPTY_INPUT
            );
        }
        return switch (validationMode) {
            case ASCII128 -> {
                if (toCompute) yield Optional.of(validateIsoAndCompute(128, input));
                validateIso(128, input);
                yield Optional.empty();
            }
            case LATIN1 -> {
                if (toCompute) yield Optional.of(validateIsoAndCompute(256, input));
                validateIso(256, input);
                yield Optional.empty();
            }
            case UNICODE -> {
                validateUnicode(input);
                yield Optional.empty();
            }
        };
    }

    private static int[] validateIsoAndCompute(
            int characters,
            String input
    ) throws InitialValidationFailedException {
        int[] existingCharacters = new int[characters];
        for (int i = 0; i < input.length(); i++) {
            char symbolCode = input.charAt(i);
            if (Character.isWhitespace(symbolCode)) {
                throw new InitialValidationFailedException(
                        ValidationFailureReason.NOT_A_SINGLE_WORD
                );
            }
            if (symbolCode >= characters) {
                throw new InitialValidationFailedException(
                        ValidationFailureReason.UNSUPPORTED_SYMBOL_ENCODING
                );
            }
            existingCharacters[symbolCode] = existingCharacters[symbolCode] + 1;
        }
        return existingCharacters;
    }

    private static void validateIso(
            int characters,
            String input
    ) throws InitialValidationFailedException {
        for (int i = 0; i < input.length(); i++) {
            char symbolCode = input.charAt(i);
            if (Character.isWhitespace(symbolCode)) {
                throw new InitialValidationFailedException(
                        ValidationFailureReason.NOT_A_SINGLE_WORD
                );
            }
            if (symbolCode >= characters) {
                throw new InitialValidationFailedException(
                        ValidationFailureReason.UNSUPPORTED_SYMBOL_ENCODING
                );
            }
        }
    }

    private static void validateUnicode(
            String input
    ) throws InitialValidationFailedException {
        for (int i = 0; i < input.length(); ) {
            int cp = input.codePointAt(i);
            if (Character.isWhitespace(cp)) {
                throw new InitialValidationFailedException(
                        ValidationFailureReason.NOT_A_SINGLE_WORD
                );
            }
            char c = input.charAt(i);
            if (Character.isSurrogate(c) && cp == c) {
                throw new InitialValidationFailedException(
                        ValidationFailureReason.UNSUPPORTED_SYMBOL_ENCODING
                );
            }
            i += Character.charCount(cp);
        }
    }

}
