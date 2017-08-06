package com.noob.expression.abstracts.impl;


import com.noob.expression.abstracts.AbstractBetweenFunction;

/**
 * ( X, Y )
 * Created by noob on 2016/6/22.
 */
public class BetweenLoroFunction extends AbstractBetweenFunction {
    @Override
    public String getName() {
        return "between_loro";
    }

    @Override
    public boolean judge(double judgeVal, double lowerLimit, double upperLimit) {
        return judgeVal > lowerLimit && judgeVal < upperLimit;
    }
}
