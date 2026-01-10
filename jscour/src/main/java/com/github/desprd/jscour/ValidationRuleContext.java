package com.github.desprd.jscour;

import java.util.Optional;

public record ValidationRuleContext(String input, Optional<int[]> existingCharactersArray) {}
