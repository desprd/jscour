package com.github.desprd.jscour;

final class InitialValidationFailedException extends Exception{

    final ValidationFailureReason failureReason;

    public InitialValidationFailedException(ValidationFailureReason failureReason) {
        this.failureReason = failureReason;
    }
}