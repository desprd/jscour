package com.github.desprd.jscour;

public interface ValidationRulesStep {
    ValidationRulesStep blockOffensiveWords();
    ValidationRulesStep englishOnly();
    ValidationRulesStep charactersLimit(int limit);
    Validator build();
}
