package net.sourceforge.jeval.function.math.abstracts;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionConstants;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionResult;

/**
 * Created by xiongwenjun on 2016/6/23.
 */
public abstract class AbstractFunction implements Function {

    public static final String DEFAULT_RESULT = "0";
    public static final String EMPTY = "";

    /**
     * default result is double.
     *
     * @param evaluator
     * @param arguments
     * @return
     * @throws FunctionException
     */
    public FunctionResult execute(Evaluator evaluator, String arguments)
            throws FunctionException {
        try {
            return Function.isNotBlank(arguments) ?
                    executeFormula(evaluator, arguments.replaceAll("\\s", EMPTY)) :
                    numberResult(null);
        } catch (FunctionException fe) {
            throw new FunctionException(fe.getMessage(), fe);
        } catch (Exception e) {
            throw new FunctionException("execute formula exception.", e);
        }
    }

    public abstract FunctionResult executeFormula(Evaluator evaluator, String arguments) throws FunctionException;

    public String trimAndRemoveQuoteChars(String arguments, char quoteCharacter) throws FunctionException {
        String trimmedArgument = arguments.trim();
        if (trimmedArgument.charAt(0) == quoteCharacter) {
            trimmedArgument = trimmedArgument.substring(1, trimmedArgument.length());
            if (trimmedArgument.charAt(trimmedArgument.length() - 1) == quoteCharacter) {
                trimmedArgument = trimmedArgument.substring(0, trimmedArgument.length() - 1);
            } else {
                throw new FunctionException("Value does not end with a quote.");
            }
        }
        return trimmedArgument;
    }

    public FunctionResult stringResult(String result) throws FunctionException {
        return new FunctionResult(result == null ? EMPTY : result,
                FunctionConstants.FUNCTION_RESULT_TYPE_STRING);
    }

    public FunctionResult numberResult(String result) throws FunctionException {
        return new FunctionResult(result == null ? DEFAULT_RESULT : result,
                FunctionConstants.FUNCTION_RESULT_TYPE_NUMERIC);
    }
}
