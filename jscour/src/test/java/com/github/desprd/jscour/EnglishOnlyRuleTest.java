package com.github.desprd.jscour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnglishOnlyRuleTest {
    EnglishOnlyRule rule;

    @BeforeEach
    void init() {
        rule = new EnglishOnlyRule(ValidationFailureReason.NON_ENGLISH_CHARACTERS);
    }

    @Test
    void englishLettersInput_asciiMode_isValid_returnTrue() {
        // Given
        ValidationRuleContext context = new ValidationRuleContext(
                "abc",
                Optional.empty(),
                ValidationMode.ASCII128
        );

        // When
        boolean result = rule.isValid(context);

        // Then
        assertTrue(result);
    }

    @Test
    void nonEnglishLettersInput_asciiMode_isValid_returnFalse() {
        // Given
        ValidationRuleContext context = new ValidationRuleContext(
                "abc1",
                Optional.empty(),
                ValidationMode.ASCII128
        );

        // When
        boolean result = rule.isValid(context);

        // Then
        assertFalse(result);
    }

    @Test
    void englishLettersInput_unicodeMode_isValid_returnTrue() {
        // Given
        ValidationRuleContext context = new ValidationRuleContext(
                "abc",
                Optional.empty(),
                ValidationMode.UNICODE
        );

        // When
        boolean result = rule.isValid(context);

        // Then
        assertTrue(result);
    }

    @Test
    void nonEnglishLettersInput_unicodeMode_isValid_returnFalse() {
        // Given
        ValidationRuleContext context = new ValidationRuleContext(
                "abc1",
                Optional.empty(),
                ValidationMode.UNICODE
        );

        // When
        boolean result = rule.isValid(context);

        // Then
        assertFalse(result);
    }

}
