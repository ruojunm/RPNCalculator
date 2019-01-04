package com.ruojunm.rpn.message;

public class OperatorInputErrorException extends RuntimeException {
    private int position;
    private String operator;

    public OperatorInputErrorException(int position, String operator, String systemErrorMessage) {
        super(systemErrorMessage);
        this.position = position;
        this.operator = operator;
    }

    public String getMessage() {
        return String.format("operator %s (position: %s): insufficient parameters", this.operator, this.position + 1); //todo i18n
    }
}
