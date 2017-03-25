package com.noob.core.abstracts.impl;

import com.noob.core.abstracts.AbstractFunction;
import net.sourceforge.jeval.EvaluationConstants;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionHelper;
import net.sourceforge.jeval.function.FunctionResult;

import java.util.ArrayList;

/**
 * nvl_string(judgeVal, replaceVal)
 * <p>
 * All the parameters are string.
 * <p>
 * IF judgeVal == null RETURN  replaceVal ELSE  RETURN judgeVal
 * <p>
 * Created by noob on 2016/6/23.
 */
public class NvlStringFunction extends AbstractFunction {

    @Override
    public String getName() {
        return "nvl_string";
    }


    @Override
    public FunctionResult executeFormula(Evaluator evaluator, String arguments) throws FunctionException {
        String result = null;

        ArrayList<String> list = FunctionHelper.getStrings(arguments, EvaluationConstants.FUNCTION_ARGUMENT_SEPARATOR);
        // size must be 2
        if (list.size() == 2) {
            char quoteCharacter = evaluator.getQuoteCharacter();

            String judgeVal = trimAndRemoveQuoteChars(list.get(0), quoteCharacter);
            String replaceVal = trimAndRemoveQuoteChars(list.get(1), quoteCharacter);

            result = Function.isNotBlank(judgeVal) ? judgeVal : replaceVal;
        }

        return stringResult(result);
    }


}
