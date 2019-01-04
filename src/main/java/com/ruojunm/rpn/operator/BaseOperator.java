package com.ruojunm.rpn.operator;

import com.ruojunm.rpn.RPNCalculator;
import com.ruojunm.rpn.element.NumberElement;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseOperator implements OperatorCmd {
    public static Set<String> validOperatorSet = Arrays.stream("+, -, *, /, sqrt, undo, clear".split(","))
            .map(String::trim).collect(Collectors.toCollection(HashSet::new));

    public static boolean isAValidOperator(String s) {
        return validOperatorSet.contains(s);
    }

    public static OperatorCmd newInstance(String s) {
        OperatorCmd cmd = null;
        switch (s) {
            case "+":
                cmd = new Add();
                break;
            case "-":
                cmd = new Subtract();
                break;
            case "*":
                cmd = new Multiply();
                break;
            case "/":
                cmd = new Divide();
                break;
            case "sqrt":
                cmd = new Sqrt();
                break;
            case "clear":
                cmd = new Clear();
                break;
            case "undo":
                cmd = new Undo();
                break;
            default:
                cmd = new NumberElement(new BigDecimal(s));
                break;
        }

        return cmd;
    }


    /**
     * position is a value to record the user input operator's position
     */
    private int position;
    /**
     * originalInput is the user input. This is used to present error msg to users
     */
    private String originalInput;


    public String getOriginalInput() {
        return originalInput;
    }

    public void setOriginalInput(String originalInput) {
        this.originalInput = originalInput;
    }

    /**
     * We design position attribute here , is because the requirement asks to show an error msg which needs to contain the position info.
     *
     * @param position
     * @return
     */
    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public abstract void execute(RPNCalculator.RPNContext context);

    public abstract void rollback(RPNCalculator.RPNContext context);

}
