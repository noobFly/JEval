package com.noob.expression.abstracts.impl;

import com.noob.expression.abstracts.AbstractFunction;
import com.noob.expression.EvaluationConstants;
import com.noob.expression.Evaluator;
import com.noob.expression.function.FunctionException;
import com.noob.expression.function.FunctionHelper;
import com.noob.expression.function.FunctionResult;

import java.util.ArrayList;

/**
 * decode_string(judgeVal, defaultVal, param1, return1, param2, return2 ...)
 * <p>
 * judgeVal is string, defaultVal is double, param is String, return is double;
 * <p>
 * IF judgeVal.equals(param1)  RETURN return1  ELSE IF judgeVal.equals(param2)  RETURN return2  ...   ELSE  RETURN defaultVal
 * <p>
 * Created by noob on 2016/6/23.
 */
public class DecodeStringFunction extends AbstractFunction {

    @Override
    public String getName() {
        return "decode_string";
    }

    @Override
    public FunctionResult executeFormula(Evaluator evaluator, String arguments) throws FunctionException {
        String result = null;

        ArrayList<String> list = FunctionHelper.getStrings(arguments, EvaluationConstants.FUNCTION_ARGUMENT_SEPARATOR);
        int size = list.size();
        // size must be greater than 1
        if (size > 1) {
            String judgeVal = list.get(0);
            String defaultVal = list.get(1);

            for (int i = 2; i < size - 1; i += 2) {
                if (judgeVal.equals(list.get(i))) {
                    result = list.get(i + 1);
                    break;
                }

            }

            if (result == null)
                result = defaultVal;
        }


        return numberResult(result);
    }

}
