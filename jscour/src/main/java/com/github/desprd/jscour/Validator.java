package com.github.desprd.jscour;

public final class Validator {

    private final boolean blockBadWords;
    private final boolean englishOnly;
    private final int charactersLimit;

    Validator(boolean blockBadWords, boolean englishOnly, int charactersLimit) {
        this.blockBadWords = blockBadWords;
        this.englishOnly = englishOnly;
        this.charactersLimit = charactersLimit;
    }

    public void validate(String word) {

    }
}
