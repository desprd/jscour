package com.github.desprd.jscour;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharactersLimitRuleTest {
    CharactersLimitRule rule;

    @BeforeEach
    void init() {
        rule = new CharactersLimitRule(ValidationFailureReason.TOO_LONG, 5);
    }

    @Test
    void inputShorterThanLimit_isValid_returnTrue() {
        // Given
        ValidationRuleContext input = new ValidationRuleContext(
                "aaaa",
                Optional.empty()
        );

        // When
        boolean result = rule.isValid(input);

        // Then
        assertTrue(result);
    }

    @Test
    void inputLongerThanLimit_isValid_returnFalse() {
        // Given
        ValidationRuleContext input = new ValidationRuleContext(
                "aaaaaa",
                Optional.empty()
        );

        // When
        boolean result = rule.isValid(input);

        // Then
        assertFalse(result);
    }

}
