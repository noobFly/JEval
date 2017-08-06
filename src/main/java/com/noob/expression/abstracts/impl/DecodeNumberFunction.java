package com.noob.expression.abstracts.impl;

import com.noob.expression.abstracts.AbstractFunction;
import com.noob.expression.EvaluationConstants;
import com.noob.expression.Evaluator;
import com.noob.expression.function.FunctionException;
import com.noob.expression.function.FunctionHelper;
import com.noob.expression.function.FunctionResult;

import java.util.ArrayList;

/**
 * decode_number(judgeVal, defaultVal, param1, return1, param2, return2 ...)
 * <p>
 * All the parameters are double.
 * <p>
 * IF judgeVal=param1  RETURN return1  ELSE IF judgeVal=param2  RETURN return2  ...   ELSE  RETURN defaultVal
 * Created by noob on 2016/6/23.
 */
public class DecodeNumberFunction extends AbstractFunction {

    @Override
    public String getName() {
        return "decode_number";
    }

    @Override
    public FunctionResult executeFormula(Evaluator evaluator, String arguments) throws FunctionException {
        Double result = null;

        ArrayList<Double> list = FunctionHelper.getDoubles(arguments, EvaluationConstants.FUNCTION_ARGUMENT_SEPARATOR);
        int size = list.size();
        // size must be greater than 1
        if (size > 1) {
            double judgeVal = list.get(0).doubleValue();
            double defaultVal = list.get(1).doubleValue();

            for (int i = 2; i < size - 1; i += 2) {

                if (judgeVal == list.get(i).doubleValue()) {
                    result = list.get(i + 1).doubleValue();
                    break;
                }
            }

            if (result == null)
                result = defaultVal;
        }


        return numberResult(Double.toString(result));
    }
}
