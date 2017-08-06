package com.noob.expression.process;

import java.util.Map;

/**
 * analytic algorithm gateway
 * Created by noob on 2016/6/28.
 */
public interface IAnalyticAlgorithm {

    String RATIO_SUFFIX = ".ratio";
    String DIMENSION_VALUE = "dimension_value";
    String DENOMINATOR = "100";
    String PERIOD = "\\.";
    String DIMENSION = "DIMENSION";
    String MODEL = "MODEL";

     class SelectType {
        /**
         * 数字型
         */
        public static final String NUMBER = "1";
        /**
         * 字符串
         */
        public static final String CHARACTER = "2";
    }

    String analyticNumber(String rule, Map<String, String> parameters) throws Exception;

    String analytic(String rule, String customerDetail, String dimensionType) throws Exception;
}
