package com.ruojunm.rpn.message;

import com.ruojunm.rpn.RPNCalculator;

public class MessageHelper {
    public static String getErrorMessge(Exception e, RPNCalculator.RPNContext context){
        if(e instanceof OperatorInputErrorException){
            return e.getMessage();
        }
        return String.format("operator %s (position: %s): " + generalInputErrorMsg(),
                context.getCurrentOperatorStr(), context.getCurrentOperatorPosition() + 1); //todo i18n
    }

    private static String generalInputErrorMsg(){
        return "The input causes a number format error or divided by 0";// to do i18n
    }
}
