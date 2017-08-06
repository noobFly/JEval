package com.noob.expression;

import com.noob.expression.function.TestFunction;
import com.noob.expression.function.TestRuleFunction;

/**
 * Contains a couple of samples for evaluating expression containing custom
 * functions. There are many more examples in the JUnit tests.
 */
public class TestSample {

    /**
     * Run the sample code. No arguments are necessary.
     *
     * @param args
     */
    public static void main(String args[]) {

        Evaluator evaluator = new Evaluator();

        try {
            System.out.println(evaluator
                    .evaluate("max(36,min(12302.272727272726+ 3.479910815066026+3075.0,100))"));
            System.out.println(evaluator
                    .evaluate("startsWith('test', 'te', 1)"));
            evaluator.putFunction(new TestFunction());
            evaluator.putFunction(new TestRuleFunction());
            evaluator.putVariable("x1", "5");
            evaluator.putVariable("x2", "1");
            evaluator.putVariable("x3", "3");
            evaluator.putVariable("x4", "1");
            evaluator.putVariable("x5", "4");
            evaluator.putVariable("x6", "6");
            evaluator.putVariable("x7", "7");

            System.out.println(evaluator
                    .evaluate("RANGERULE(30,0,10,min(10,20),10,20,asin(60),20,30,cos(60)) + '20'"));

            System.out.println(evaluator
                    .evaluate("rangexx(rangexx(#{x1},#{x2},#{x3},#{x4},#{x5},#{x6},#{x7}),1,4,1,5,8,2)"));


/*            evaluator.clearFunctions();

            evaluator.clearVariables();
*/
            System.out.println(evaluator.evaluate("min(rangexx(rangexx(#{x1},#{x2},#{x3},#{x4},#{x5},#{x6},#{x7}),1,4,1,5,8,2)*10,100)"));
        } catch (EvaluationException ee) {
            System.out.println(ee);
        }
    }
}
