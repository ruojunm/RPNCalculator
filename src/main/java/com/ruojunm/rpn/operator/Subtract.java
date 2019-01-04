package com.ruojunm.rpn.operator;

import java.math.BigDecimal;

public class Subtract extends Add{
    protected BigDecimal compute(){
        return first.getValue().subtract(second.getValue());
    }
}
