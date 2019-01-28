package com.noob.expression.process;


import com.noob.expression.abstracts.AbstractFunction;
import com.noob.expression.abstracts.impl.*;
import com.noob.expression.EvaluationConstants;
import com.noob.expression.EvaluationException;
import com.noob.expression.Evaluator;
import com.noob.expression.function.Function;

import java.util.Map;

/**
 * Created by noob on 2016/6/28.
 */
public class AnalyticAlgorithm implements IAnalyticAlgorithm {
    final BetweenLcrcFunction between_lcrc = new BetweenLcrcFunction();
    final BetweenLcroFunction between_lcro = new BetweenLcroFunction();
    final BetweenLorcFunction between_lorc = new BetweenLorcFunction();
    final BetweenLoroFunction between_loro = new BetweenLoroFunction();
    final DecodeNumberFunction decode_number = new DecodeNumberFunction();
    final DecodeStringFunction decode_string = new DecodeStringFunction();
    final NvlStringFunction nvl_string = new NvlStringFunction();
    final Nvl2StringFunction nvl2_string = new Nvl2StringFunction();
    final Nvl2NumberFunction nvl2_number = new Nvl2NumberFunction();

    ThreadLocal<Evaluator> threadLocal = new ThreadLocal<Evaluator>() {
        @Override
        protected Evaluator initialValue() {
            Evaluator evaluator = new Evaluator();
            evaluator.putFunction(between_lcrc);
            evaluator.putFunction(between_lcro);
            evaluator.putFunction(between_lorc);
            evaluator.putFunction(between_loro);
            evaluator.putFunction(decode_number);
            evaluator.putFunction(decode_string);
            evaluator.putFunction(nvl_string);
            evaluator.putFunction(nvl2_string);
            evaluator.putFunction(nvl2_number);
            return evaluator;
        }
    };

    private Evaluator getEvaluator() {
        return threadLocal.get();
    }


    @Override
    public String analyticNumber(String rule, Map<String, String> parameters) throws Exception {
        String result = null;
        long startTime = System.currentTimeMillis();
        if (Function.isNotBlank(rule)) {
            if (parameters != null && parameters.size() > 0) {
                parameters.forEach((k, v) -> {
                            if (Function.isNotBlank(k)) {
                                getEvaluator().putVariable(k, Function.isNotBlank(v) ? v : AbstractFunction.DEFAULT_RESULT);
                            }
                        }
                );
            }
            result = evaluate(MODEL, rule, parameters);
        }
        System.out.println(String.format("------last: analytic number end.------ rule:%s, variables:%s, result:%s, cost:%sms.", rule, parameters.toString(), result.toString(), System.currentTimeMillis() - startTime));
        return result;
    }


    @Override
    public String analytic(String rule, String detail, String dimensionType) throws Exception {


        String result = null;
        long startTime = System.currentTimeMillis();

        if (Function.isNotBlank(rule) && Function.isNotBlank(dimensionType)) {
            String variable;
            if (SelectType.NUMBER.equals(dimensionType)) {
                variable = Function.isNotBlank(detail) ? detail : AbstractFunction.DEFAULT_RESULT;
            } else {
                variable = EvaluationConstants.SINGLE_QUOTE + (Function.isNotBlank(detail) ? detail : Function.EMPTY) + EvaluationConstants.SINGLE_QUOTE;

            }
            getEvaluator().putVariable(IAnalyticAlgorithm.DIMENSION_VALUE, variable);
            result = evaluate(DIMENSION, rule, variable);
        }

        System.out.println(String.format("------last: analytic end.------ rule:%s, detail:%s, dimensionType:%s, result:%s, cost:%sms.",

                rule, detail, dimensionType, result.toString(), System.currentTimeMillis() - startTime));
        return result;
    }


    private <T> String evaluate(String prefix, String rule, T parameters) throws EvaluationException {
        String result;
        try {
            result = getEvaluator().evaluate(rule);
        } catch (Exception e) {
            StringBuffer sb = new StringBuffer("--").append(prefix).append("--").append("rule: ").
                    append(rule).append(", parameters: ").
                    append(parameters.toString()).
                    append(", cause: ").append(e.getMessage());
            throw new EvaluationException(sb.toString(), e);

        } finally {
            getEvaluator().clearVariables();
        }

        return isNumber(result) ? result : AbstractFunction.DEFAULT_RESULT;
    }


    private boolean isNumber(String str) {
        // TODO jude the arg is number
        return str != null && true;
    }


    public static void main(String[] args) {
        final BetweenLcrcFunction between_lcrc = new BetweenLcrcFunction();
        final BetweenLcroFunction between_lcro = new BetweenLcroFunction();
        final BetweenLorcFunction between_lorc = new BetweenLorcFunction();
        final BetweenLoroFunction between_loro = new BetweenLoroFunction();
        final DecodeNumberFunction decode_number = new DecodeNumberFunction();
        final DecodeStringFunction decode_string = new DecodeStringFunction();
        final NvlStringFunction nvl_string = new NvlStringFunction();
        final Nvl2StringFunction nvl2_string = new Nvl2StringFunction();
        final Nvl2NumberFunction nvl2_number = new Nvl2NumberFunction();
        Evaluator evaluator = new Evaluator();
        evaluator.putFunction(between_lcrc);
        evaluator.putFunction(between_lcro);
        evaluator.putFunction(between_lorc);
        evaluator.putFunction(between_loro);
        evaluator.putFunction(decode_number);
        evaluator.putFunction(decode_string);
        evaluator.putFunction(nvl_string);
        evaluator.putFunction(nvl2_string);
        evaluator.putFunction(nvl2_number);
        evaluator.putVariable("M_CFZXZMX_TGW9", "3");
        try {
            System.out.println(evaluator.evaluate("100*min(#{M_CFZXZMX_TGW9},20)+100*max(min(#{M_CFZXZMX_TGW9}-20,80),0)-(pow(max(min(#{M_CFZXZMX_TGW9}-20,80),0),2)+max(min(#{M_CFZXZMX_TGW9}-20,80),0))*0.5*1+20*max(min(#{M_CFZXZMX_TGW9}-100,400),0)"));
        } catch (EvaluationException e) {
            e.printStackTrace();
        }
    }
}
