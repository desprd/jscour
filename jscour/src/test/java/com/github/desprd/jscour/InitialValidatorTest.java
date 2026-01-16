package com.github.desprd.jscour;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InitialValidatorTest {

    @Test
    void nullAsInput_initValidate_throwsInitialValidationFailedException() {
        // Given / When
        InitialValidationFailedException e = assertThrows(
                InitialValidationFailedException.class,
                ()-> InitialValidation.initValidate(
                        null,
                        ValidationMode.ASCII128,
                        false
                )
        );

        // Then
        assertEquals(ValidationFailureReason.EMPTY_INPUT, e.getFailureReason());
    }

    @Test
    void blankInput_initValidate_throwsInitialValidationFailedException() {
        // Given / When
        InitialValidationFailedException e = assertThrows(
                InitialValidationFailedException.class,
                ()-> InitialValidation.initValidate(
                        "",
                        ValidationMode.ASCII128,
                        false
                )
        );

        // Then
        assertEquals(ValidationFailureReason.EMPTY_INPUT, e.getFailureReason());
    }

    @Test
    void validAscii128Input_Ascii128ValidMode_toComputeFalse_returnOptionalEmpty() throws Exception{
        // Given / When
        Optional<int[]> result = InitialValidation.initValidate(
                "abc",
                ValidationMode.ASCII128,
                false
        );

        // Then
        assertEquals(Optional.empty(), result);
    }

    @Test
    void validAscii128Input_Ascii128ValidMode_toComputeTrue_returnOptionalArrayOfIndexes() throws Exception{
        // Given
        int[] ascii = new int[128];
        ascii[97] = 1;
        ascii[98] = 1;
        ascii[99] = 1;

        // When
        Optional<int[]> result = InitialValidation.initValidate(
                "abc",
                ValidationMode.ASCII128,
                true
        );

        // Then
        assertArrayEquals(ascii, result.get());

    }

    @Test
    void whiteSpaceInAscii128Input_Ascii128ValidMode_toComputeFalse_throwsInitialValidationFailedException() {
        // Given / When
        InitialValidationFailedException e = assertThrows(
                InitialValidationFailedException.class,
                () -> InitialValidation.initValidate(
                        "abc d",
                        ValidationMode.ASCII128,
                        false
                )
        );

        // Then
        assertEquals(ValidationFailureReason.NOT_A_SINGLE_WORD, e.getFailureReason());
    }

    @Test
    void invalidCharacterInAscii128Input_Ascii128ValidMode_toComputeFalse_throwsInitialValidationFailedException() {
        // Given / When
        InitialValidationFailedException e = assertThrows(
                InitialValidationFailedException.class,
                () -> InitialValidation.initValidate(
                        "abcó",
                        ValidationMode.ASCII128,
                        false
                )
        );

        // Then
        assertEquals(ValidationFailureReason.UNSUPPORTED_SYMBOL_ENCODING, e.getFailureReason());
    }

    @Test
    void whiteSpaceInAscii128Input_Ascii128ValidMode_toComputeTrue_throwsInitialValidationFailedException() {
        // Given / When
        InitialValidationFailedException e = assertThrows(
                InitialValidationFailedException.class,
                () -> InitialValidation.initValidate(
                        "abc d",
                        ValidationMode.ASCII128,
                        true
                )
        );

        // Then
        assertEquals(ValidationFailureReason.NOT_A_SINGLE_WORD, e.getFailureReason());
    }

    @Test
    void invalidCharacterInAscii128Input_Ascii128ValidMode_toComputeTrue_throwsInitialValidationFailedException() {
        // Given / When
        InitialValidationFailedException e = assertThrows(
                InitialValidationFailedException.class,
                () -> InitialValidation.initValidate(
                        "abcł",
                        ValidationMode.ASCII128,
                        true
                )
        );

        // Then
        assertEquals(ValidationFailureReason.UNSUPPORTED_SYMBOL_ENCODING, e.getFailureReason());
    }

    @Test
    void validLatin1Input_Latin1ValidMode_toComputeFalse_returnOptionalEmpty() throws Exception{
        // Given / When
        Optional<int[]> result = InitialValidation.initValidate(
                "üóá=",
                ValidationMode.LATIN1,
                false
        );

        // Then
        assertEquals(Optional.empty(), result);
    }

    @Test
    void validLatin1Input_Latin1ValidMode_toComputeTrue_returnOptionalArrayOfIndexes() throws Exception{
        // Given
        int[] ascii = new int[256];
        ascii['ü'] = 1;
        ascii['ó'] = 1;
        ascii['á'] = 1;

        // When
        Optional<int[]> result = InitialValidation.initValidate(
                "üóá",
                ValidationMode.LATIN1,
                true
        );

        // Then
        assertArrayEquals(ascii, result.get());

    }

    @Test
    void whiteSpaceInLatin1Input_Latin1ValidMode_toComputeFalse_throwsInitialValidationFailedException() {
        // Given / When
        InitialValidationFailedException e = assertThrows(
                InitialValidationFailedException.class,
                () -> InitialValidation.initValidate(
                        "üóá ë",
                        ValidationMode.LATIN1,
                        false
                )
        );

        // Then
        assertEquals(ValidationFailureReason.NOT_A_SINGLE_WORD, e.getFailureReason());
    }

    @Test
    void invalidCharacterInLatin1Input_Latin1ValidMode_toComputeFalse_throwsInitialValidationFailedException() {
        // Given / When
        InitialValidationFailedException e = assertThrows(
                InitialValidationFailedException.class,
                () -> InitialValidation.initValidate(
                        "üóáł",
                        ValidationMode.LATIN1,
                        false
                )
        );

        // Then
        assertEquals(ValidationFailureReason.UNSUPPORTED_SYMBOL_ENCODING, e.getFailureReason());
    }

    @Test
    void whiteSpaceInLatin1Input_Latin1ValidMode_toComputeTrue_throwsInitialValidationFailedException() {
        // Given / When
        InitialValidationFailedException e = assertThrows(
                InitialValidationFailedException.class,
                () -> InitialValidation.initValidate(
                        "üóá ë",
                        ValidationMode.LATIN1,
                        true
                )
        );

        // Then
        assertEquals(ValidationFailureReason.NOT_A_SINGLE_WORD, e.getFailureReason());
    }

    @Test
    void invalidCharacterInLatin1Input_Latin1ValidMode_toComputeTrue_throwsInitialValidationFailedException() {
        // Given / When
        InitialValidationFailedException e = assertThrows(
                InitialValidationFailedException.class,
                () -> InitialValidation.initValidate(
                        "üóáł",
                        ValidationMode.LATIN1,
                        true
                )
        );

        // Then
        assertEquals(ValidationFailureReason.UNSUPPORTED_SYMBOL_ENCODING, e.getFailureReason());
    }

    @Test
    void validUnicodeInput_UnicodeValidMode_returnOptionalEmpty() throws Exception{
        // Given / When
        Optional<int[]> result = InitialValidation.initValidate(
                "abc",
                ValidationMode.UNICODE,
                false
        );

        // Then
        assertEquals(Optional.empty(), result);
    }

    @Test
    void whiteSpaceInUnicodeInput_UnicodeValidMode_throwsInitialValidationFailedException() {
        // Given / When
        InitialValidationFailedException e = assertThrows(
                InitialValidationFailedException.class,
                () -> InitialValidation.initValidate(
                        "abc d",
                        ValidationMode.UNICODE,
                        false
                )
        );

        // Then
        assertEquals(ValidationFailureReason.NOT_A_SINGLE_WORD, e.getFailureReason());
    }

    @Test
    void invalidCharacterInUnicodeInput_UnicodeValidMode_throwsInitialValidationFailedException() {
        // Given / When
        InitialValidationFailedException e = assertThrows(
                InitialValidationFailedException.class,
                () -> InitialValidation.initValidate(
                        "abc\uD800d",
                        ValidationMode.UNICODE,
                        false
                )
        );

        // Then
        assertEquals(ValidationFailureReason.UNSUPPORTED_SYMBOL_ENCODING, e.getFailureReason());
    }

}
