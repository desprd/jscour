package com.github.desprd.jscour;

public final class ValidatorBuilder {

    private boolean blockBadWords = true;
    private boolean englishOnly = true;
    private int charactersLimit = Integer.MAX_VALUE;

    public static ValidatorBuilder builder() {
        return new ValidatorBuilder();
    }

    public ValidatorBuilder enableBlockingBadWords(boolean enable) {
        this.blockBadWords = enable;
        return this;
    }

    public ValidatorBuilder enableEnglishOnly(boolean enable) {
        this.englishOnly = enable;
        return this;
    }

    public ValidatorBuilder charactersLimit(int limit) {
        this.charactersLimit = limit;
        return this;
    }

    public Validator build() {
        return new Validator(blockBadWords, englishOnly, charactersLimit);
    }



}