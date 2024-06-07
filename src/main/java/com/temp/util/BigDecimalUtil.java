package com.temp.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * BigDecimal 工具类
 *
 * @author Hollis
 * @since 2024/06/05 下午2:36
 */
public class BigDecimalUtil {

    private static final BigDecimal TEN = BigDecimal.valueOf(10);
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    /**
     * 入参为 null 时, 返回 0, 否则返回原值
     *
     * @param param 参数
     * @return {@link BigDecimal } 原值 或者 0
     */
    public static BigDecimal parseNullToZero(BigDecimal param) {
        return Optional.ofNullable(param).orElse(BigDecimal.ZERO);
    }

    /**
     * 乘以一百 <br/>
     * 入参为 null 时, 返回 null
     *
     * @param param 任意数值
     * @return {@link BigDecimal } 乘以一百后的结果, 默认返回 0
     */
    public static BigDecimal multiplyHundred(BigDecimal param) {
        if (param == null) {
            return null;
        }
        return param.multiply(HUNDRED);
    }

    /**
     * 除以一百, 四舍五入, 保留 "2" 位小数 <br/>
     * 入参为 null 时, 返回 null
     *
     * @param param 任意数值
     * @return {@link BigDecimal } 除以一百, 四舍五入, 保留2位小数, 默认返回null
     */
    public static BigDecimal divideHundredAndScale2(BigDecimal param) {
        return divideHundred(param, 2);
    }

    /**
     * 除以一百, 四舍五入, 保留 "8" 位小数 <br/>
     * 入参为 null 时, 返回 null
     *
     * @param param 任意数值
     * @return {@link BigDecimal } 除以一百, 四舍五入, 保留8位小数, 默认返回null
     */
    public static BigDecimal divideHundredAndScale8(BigDecimal param) {
        return divideHundred(param, 8);
    }

    /**
     * 除以一百, 四舍五入, 保留指定位小数 <br/>
     * 入参为 null 时, 返回 null
     *
     * @param param 任意数值
     * @param scale 保留小数位数
     * @return {@link BigDecimal } 除以一百, 四舍五入,保留指定位小数, 默认返回null
     */
    public static BigDecimal divideHundred(BigDecimal param, int scale) {
        if (param == null) {
            return null;
        }
        return param.divide(HUNDRED, scale, RoundingMode.HALF_UP);
    }
}
