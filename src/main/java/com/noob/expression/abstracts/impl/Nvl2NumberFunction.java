package com.noob.expression.abstracts.impl;
import com.noob.expression.abstracts.AbstractFunction;
import com.noob.expression.function.Function;

import com.noob.expression.EvaluationConstants;
import com.noob.expression.Evaluator;
import com.noob.expression.function.FunctionException;
import com.noob.expression.function.FunctionHelper;
import com.noob.expression.function.FunctionResult;

import java.util.ArrayList;

/**
 * nvl2_number(judgeVal, replace_notNull, replace_null)
 * <p>
 * judgeVal is string, replace_notNull and  replace_null are double
 * <p>
 * IF judgeVal != null RETURN  replace_notNull ELSE  RETURN replace_null
 * Created by noob on 2016/6/23.
 */
public class Nvl2NumberFunction extends AbstractFunction {
    @Override
    public String getName() {
        return "nvl2_number";
    }

    @Override
    public FunctionResult executeFormula(Evaluator evaluator, String arguments) throws FunctionException {
        String result = null;

        ArrayList<String> list = FunctionHelper.getStrings(arguments, EvaluationConstants.FUNCTION_ARGUMENT_SEPARATOR);
        // size must be 3
        if (list.size() == 3) {
            String judgeVal = FunctionHelper.trimAndRemoveQuoteChars(
                    list.get(0), evaluator.getQuoteCharacter());
            result = Function.isNotBlank(judgeVal) ? list.get(1) : list.get(2);
        }

        return numberResult(result);
    }

}
