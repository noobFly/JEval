package com.noob.core.abstracts.impl;


import com.noob.core.abstracts.AbstractBetweenFunction;

/**
 * [ X, Y )
 * Created by noob on 2016/6/22.
 */
public class BetweenLcroFunction extends AbstractBetweenFunction {
    @Override
    public String getName() {
        return "between_lcro";
    }

    @Override
    public boolean judge(double judgeVal, double lowerLimit, double upperLimit) {
        return judgeVal >= lowerLimit && judgeVal < upperLimit;
    }
}
