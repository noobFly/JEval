package net.sourceforge.jeval.function.math.abstracts;

import net.sourceforge.jeval.EvaluationConstants;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionHelper;
import net.sourceforge.jeval.function.FunctionResult;

import java.util.ArrayList;

/**
 * Range Formula;  All the parameters are double.
 * <p>
 * construction: (judgeVal, defaultVal, lowerLimit1, upperLimit1, returnVal1, lowerLimit2, upperLimit2, returnVal2, ...)
 * <p>
 * Created by xiongwenjun on 2016/6/22.
 */
public abstract class AbstractBetweenFunction extends AbstractFunction {

    @Override
    public FunctionResult executeFormula(Evaluator evaluator, String arguments) throws FunctionException {
        Double result = null;

        ArrayList<Double> list = FunctionHelper.getDoubles(arguments, EvaluationConstants.FUNCTION_ARGUMENT_SEPARATOR);
        int size = list.size();
        // size must be greater than 1
        if (size > 1) {
            double judgeVal = list.get(0).doubleValue();
            double defaultVal = list.get(1).doubleValue();
            int skip = 2;
            for (; skip < size - 2; skip += 3) {
                double lowerLimit = list.get(skip).doubleValue();
                double upperLimit = list.get(skip + 1).doubleValue();

                if (judge(judgeVal, lowerLimit, upperLimit)) {
                    result = list.get(skip + 2).doubleValue();
                    break;
                }
            }

            // overflow [x,+∞)
            if (result == null && skip < size - 1) {
                if (judgeVal >= list.get(skip).doubleValue())
                    result = list.get(size - 1);
            }
            if (result == null)
                result = defaultVal;

        }

        return numberResult(Double.toString(result));
    }

    /**
     * judge for each one Range in implement class
     */
    public abstract boolean judge(double judgeVal, double lowerLimit, double upperLimit);
}
