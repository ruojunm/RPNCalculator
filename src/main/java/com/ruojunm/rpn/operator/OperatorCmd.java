package com.ruojunm.rpn.operator;

import com.ruojunm.rpn.RPNCalculator;
import com.ruojunm.rpn.RPNCalculator.RPNContext;

public interface OperatorCmd{
    void execute(RPNContext context);
    void rollback(RPNCalculator.RPNContext context);


    String getOriginalInput() ;

    void setOriginalInput(String originalInput);

    /**
     * We design position attribute here , is because the requirement asks to show an error msg which needs to contain the position info.
     *
     * @param position
     * @return
     */
    void setPosition(int position);

    int getPosition();
}
