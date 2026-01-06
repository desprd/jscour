package com.github.desprd.jscour;

import java.util.List;


public final class Validator{

    private final List<ValidationRule> rules;

    Validator(List<ValidationRule> rules) {
        this.rules = List.copyOf(rules);
    }

    public ValidationResult validate(String word) {
        return null;
    }
}