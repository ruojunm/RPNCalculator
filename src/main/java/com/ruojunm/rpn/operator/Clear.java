package com.ruojunm.rpn.operator;

import com.ruojunm.rpn.RPNCalculator;
import com.ruojunm.rpn.element.NumberElement;
import com.ruojunm.rpn.message.OperatorInputErrorException;

import java.util.ArrayDeque;
import java.util.Deque;

public class Clear extends BaseOperator{
    private Deque<NumberElement> stackBackup = new ArrayDeque<>();
    @Override
    public void execute(RPNCalculator.RPNContext context){
        stackBackup.addAll(context.getStack());
        context.getStack().clear();
        context.getOperationStack().addLast(this);
    }
    @Override
    public void rollback(RPNCalculator.RPNContext context) throws OperatorInputErrorException {
        context.getStack().addAll(stackBackup);
    }
}
