/*
 * Copyright 2002-2007 Robert Breidecker.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sourceforge.jeval.samples;

import net.sourceforge.jeval.EvaluationConstants;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionConstants;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionHelper;
import net.sourceforge.jeval.function.FunctionResult;

import java.util.ArrayList;

/**
 * This class is an example of a custom function that executes within Evaluator.
 * The function returns the source string in reverse order.
 */
public class TestFunction implements Function {
    /**
     * Returns the name of the function - "stringReverse".
     *
     * @return The name of this function class.
     */
    public String getName() {
        return "rangexx";
    }

    /**
     * Executes the function for the specified argument. This method is called
     * internally by Evaluator.
     *
     * @param evaluator
     *            An instance of Evaluator.
     * @param arguments
     *            A string argument that will be converted into a string that is
     *            in reverse order. The string argument(s) HAS to be enclosed in
     *            quotes. White space that is not enclosed within quotes will be
     *            trimmed. Quote characters in the first and last positions of
     *            any string argument (after being trimmed) will be removed
     *            also. The quote characters used must be the same as the quote
     *            characters used by the current instance of Evaluator. If there
     *            are multiple arguments, they must be separated by a comma
     *            (",").
     *
     * @return The source string in reverse order.
     *
     * @exception FunctionException
     *                Thrown if the argument(s) are not valid for this function.
     */
    public FunctionResult execute(Evaluator evaluator, String arguments)
            throws FunctionException {
        String result = "";
        Double success =null;

        try {

            ArrayList<Double> list = FunctionHelper.getDoubles(arguments, EvaluationConstants.FUNCTION_ARGUMENT_SEPARATOR);
            int size = list.size();
            for(int i=1; i < size; i+=3 ){
                    if(list.get(0).doubleValue() >=list.get(i).doubleValue()  && list.get(0).doubleValue() < list.get(i+1).doubleValue()){
                        success = list.get(i+2);
                    }
                if(success != null) break;
            }
        } catch (FunctionException fe) {
            throw new FunctionException(fe.getMessage(), fe);
        } catch (Exception e) {
            throw new FunctionException("One string argument is required.", e);
        }
        result = success != null ? success.toString() : "";
        return new FunctionResult(result,
                FunctionConstants.FUNCTION_RESULT_TYPE_NUMERIC);
    }
}