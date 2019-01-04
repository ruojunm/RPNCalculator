package com.ruojunm.rpn.operator;

import com.ruojunm.rpn.RPNCalculator;
import com.ruojunm.rpn.element.NumberElement;
import com.ruojunm.rpn.message.OperatorInputErrorException;

import java.math.BigDecimal;
import java.util.Deque;

public class Add extends BaseOperator{
    protected NumberElement first;
    protected NumberElement second;


    @Override
    public void execute(RPNCalculator.RPNContext context){
        Deque<NumberElement> stack = context.getStack();
        Deque<OperatorCmd> operationStack = context.getOperationStack();
        // check data validity
        this.second = stack.peekLast();
        if(second == null){
            throw new OperatorInputErrorException(this.getPosition(), this.getOriginalInput(), "Input Error");
        }else{
            stack.removeLast();
        }

        this.first = stack.peekLast();
        if(first == null){
            stack.addLast(second);  // we need 2 numbers, but in case we can get 1, instead of throwing Exception directly, we should add the valid one back, so that stack status could be rolled back.
            throw new OperatorInputErrorException(this.getPosition(), this.getOriginalInput(), "Input Error");
        }else{
            stack.removeLast();
        }
        stack.addLast(new NumberElement(compute()));
        operationStack.addLast(this);

    }

    protected BigDecimal compute(){
        return first.getValue().add(second.getValue());
    }

    @Override
    public void rollback(RPNCalculator.RPNContext context){
        Deque<NumberElement> stack = context.getStack();
        Deque<OperatorCmd> operationStack = context.getOperationStack();
        stack.removeLast();
        stack.addLast(first);
        stack.addLast(second);
        //operationStack.removeLast();
    }
}
