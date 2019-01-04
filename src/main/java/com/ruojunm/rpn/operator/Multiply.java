package com.ruojunm.rpn.operator;

import java.math.BigDecimal;

public class Multiply extends Add{
    protected BigDecimal compute(){
        return first.getValue().multiply(second.getValue());
    }
}
