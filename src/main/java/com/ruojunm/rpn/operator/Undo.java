package com.ruojunm.rpn.operator;

import com.ruojunm.rpn.RPNCalculator;

public class Undo extends BaseOperator {
    @Override
    public void execute(RPNCalculator.RPNContext context){
        OperatorCmd last = context.getOperationStack().pollLast();
        last.rollback(context);
    }
    @Override
    public void rollback(RPNCalculator.RPNContext context){
        // do nothing
    }
}
