package net.sourceforge.jeval.function.math.impl;

import com.iboxpay.credit.core.function.abstracts.AbstractFunction;
import net.sourceforge.jeval.EvaluationConstants;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionHelper;
import net.sourceforge.jeval.function.FunctionResult;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

/**
 * nvl2_string(judgeVal, replace_notNull, replace_null)
 * <p>
 * All the parameters are string.
 * <p>
 * IF judgeVal != null RETURN  replace_notNull ELSE  RETURN replace_null
 * <p>
 * Created by xiongwenjun on 2016/6/23.
 */
public class Nvl2StringFunction extends AbstractFunction {
    @Override
    public String getName() {
        return "nvl2_string";
    }

    @Override
    public FunctionResult executeFormula(Evaluator evaluator, String arguments) throws FunctionException {
        String result = null;

        ArrayList<String> list = FunctionHelper.getStrings(arguments, EvaluationConstants.FUNCTION_ARGUMENT_SEPARATOR);
        // size must be 3
        if (list.size() == 3) {
            char quoteCharacter = evaluator.getQuoteCharacter();

            String judgeVal = trimAndRemoveQuoteChars(list.get(0), quoteCharacter);
            String replace_notNull = trimAndRemoveQuoteChars(list.get(1), quoteCharacter);
            String replace_null = trimAndRemoveQuoteChars(list.get(2), quoteCharacter);

            result = StringUtils.isNotBlank(judgeVal) ? replace_notNull : replace_null;
        }


        return stringResult(result);
    }
}
