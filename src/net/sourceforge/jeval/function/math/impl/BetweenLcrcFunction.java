package net.sourceforge.jeval.function.math.impl;

import com.iboxpay.credit.core.function.abstracts.AbstractBetweenFunction;

/**
 * [ X, Y ]
 * Created by xiongwenjun on 2016/6/22.
 */
public class BetweenLcrcFunction extends AbstractBetweenFunction {
    @Override
    public String getName() {
        return "between_lcrc";
    }


    @Override
    public boolean judge(double judgeVal, double lowerLimit, double upperLimit) {
        return judgeVal >= lowerLimit && judgeVal <= upperLimit;
    }
}