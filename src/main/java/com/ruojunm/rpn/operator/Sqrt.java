package com.ruojunm.rpn.operator;

import com.ruojunm.rpn.RPNCalculator;
import com.ruojunm.rpn.element.NumberElement;
import com.ruojunm.rpn.message.OperatorInputErrorException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Deque;

public class Sqrt extends BaseOperator{
    protected NumberElement first;

    @Override
    public void execute(RPNCalculator.RPNContext context){
        Deque<NumberElement> stack = context.getStack();
        Deque<OperatorCmd> operationStack = context.getOperationStack();
        this.first = stack.peekLast();
        // check data validity
        if(first == null){
            throw new OperatorInputErrorException(this.getPosition(), this.getOriginalInput(), "Input Error");
        }else{
            stack.removeLast();
            stack.addLast(new NumberElement(compute()));
            operationStack.addLast(this);
        }

    }

    protected BigDecimal compute(){
        //  return first.getValue().sqrt(MathContext.DECIMAL64);  //this is a JDK9 API
        return new BigDecimal(Math.sqrt(first.getValue().doubleValue()));
    }

    @Override
    public void rollback(RPNCalculator.RPNContext context){
        Deque<NumberElement> stack = context.getStack();
        Deque<OperatorCmd> operationStack = context.getOperationStack();
        stack.removeLast();
        stack.add(first);
        //operationStack.removeLast();
    }
}
