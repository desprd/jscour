package com.github.desprd.jscour;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    static Validator simpleValidator;

    @BeforeAll
    static void initAll() {
        simpleValidator = ValidatorBuilder.builder().ascii128().build();
    }

    @Test
    void nullAsInput_validate_returnEMPTY_INPUT() {
        // Given / When
        ValidationResult result = simpleValidator.validate(null);

        // Then
        assertNull(result.getOriginalWord());
        assertFalse(result.isValid());
        assertEquals(ValidationFailureReason.EMPTY_INPUT, result.getReason());

    }

    @Test
    void emptyInput_validate_returnEMPTY_INPUT() {
        // Given / When
        ValidationResult result = simpleValidator.validate("");

        // Then
        assertEquals("", result.getOriginalWord());
        assertFalse(result.isValid());
        assertEquals(ValidationFailureReason.EMPTY_INPUT, result.getReason());

    }

    @Test
    void spaceAsInput_validate_returnEMPTY_INPUT() {
        // Given / When
        ValidationResult result = simpleValidator.validate(" ");

        // Then
        assertEquals(" ", result.getOriginalWord());
        assertFalse(result.isValid());
        assertEquals(ValidationFailureReason.EMPTY_INPUT, result.getReason());
    }

    @Test
    void spaceSeparatedInput_validate_returnNOT_A_SINGLE_WORD() {
        // Given / When
        ValidationResult result = simpleValidator.validate("a b");

        // Then
        assertEquals("a b", result.getOriginalWord());
        assertFalse(result.isValid());
        assertEquals(ValidationFailureReason.NOT_A_SINGLE_WORD, result.getReason());
    }

    @Test
    void tabSeparatedInput_validate_returnNOT_A_SINGLE_WORD() {
        // Given / When
        ValidationResult result = simpleValidator.validate("a\tb");

        // Then
        assertEquals("a\tb", result.getOriginalWord());
        assertFalse(result.isValid());
        assertEquals(ValidationFailureReason.NOT_A_SINGLE_WORD, result.getReason());
    }

    @Test
    void newLineSeparatedInput_validate_returnNOT_A_SINGLE_WORD() {
        // Given / When
        ValidationResult result = simpleValidator.validate("a\nb");

        // Then
        assertEquals("a\nb", result.getOriginalWord());
        assertFalse(result.isValid());
        assertEquals(ValidationFailureReason.NOT_A_SINGLE_WORD, result.getReason());
    }


    @Test
    void nonAscii128CharacterInInput_validate_returnUNSUPPORTED_SYMBOL_ENCODING() {
        // Given
        Validator asciiValidator = ValidatorBuilder.builder()
                                                   .ascii128()
                                                   .build();

        // When
        ValidationResult result = asciiValidator.validate("abcó");

        // Then
        assertEquals("abcó", result.getOriginalWord());
        assertFalse(result.isValid());
        assertEquals(ValidationFailureReason.UNSUPPORTED_SYMBOL_ENCODING, result.getReason());
    }

    @Test
    void nonLatin1CharacterInInput_validate_returnUNSUPPORTED_SYMBOL_ENCODING() {
        // Given
        Validator latin1Validator = ValidatorBuilder.builder()
                                                   .latin1()
                                                   .build();

        // When
        ValidationResult result = latin1Validator.validate("abcы");

        // Then
        assertEquals("abcы", result.getOriginalWord());
        assertFalse(result.isValid());
        assertEquals(ValidationFailureReason.UNSUPPORTED_SYMBOL_ENCODING, result.getReason());
    }

    @Test
    void unpairSurrogateInInput_validate_returnUNSUPPORTED_SYMBOL_ENCODING() {
        // Given
        Validator unicodeValidator = ValidatorBuilder.builder()
                                                    .unicode()
                                                    .build();

        // When
        ValidationResult result = unicodeValidator.validate("test\uD800test");

        // Then
        assertEquals("test\uD800test", result.getOriginalWord());
        assertFalse(result.isValid());
        assertEquals(ValidationFailureReason.UNSUPPORTED_SYMBOL_ENCODING, result.getReason());
    }
}
