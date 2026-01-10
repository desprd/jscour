package com.github.desprd.jscour;

public interface ValidationModeStep {
    ValidationRulesStep ascii128();

    ValidationRulesStep latin1();

    ValidationRulesStep unicode();
}
