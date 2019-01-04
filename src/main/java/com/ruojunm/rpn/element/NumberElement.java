package com.ruojunm.rpn.element;

import com.ruojunm.rpn.RPNCalculator;
import com.ruojunm.rpn.operator.BaseOperator;
import com.ruojunm.rpn.operator.OperatorCmd;
import com.ruojunm.rpn.operator.Rollbackable;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberElement extends BaseOperator implements Element{
    private int outputDecimalPlace = 10;  //todo read from configObject
    private BigDecimal value;

    public NumberElement(BigDecimal value){
        this.value = value;
    }
    @Override
    public String toString() {
        return value.setScale(outputDecimalPlace, RoundingMode.FLOOR).stripTrailingZeros().toPlainString();
    }

    public void rollback(RPNCalculator.RPNContext context){
        context.getStack().removeLast();
        //context.getOperationStack().removeLast();
    }



    public BigDecimal getValue() {
        return value;
    }

    @Override
    public void execute(RPNCalculator.RPNContext context) {
        context.getStack().addLast(this);
        context.getOperationStack().addLast(this);
    }
}
