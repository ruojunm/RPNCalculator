package com.ruojunm.rpn.operator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Divide extends Add{
    protected BigDecimal compute(){
        return first.getValue().divide(second.getValue(), MathContext.DECIMAL64.getPrecision(), RoundingMode.HALF_UP);
    }
}
