package amount;

import java.math.BigDecimal;

/**
 * 金额就是那工具类
 *
 * @author leiyang
 * @since 2019/5/31 9:06
 */
public class AmountUtil {

    public static final String YUAN = "100";
    public static final String THOUSAND = "1000000";

    /**
     * 将分转化为数值型 BigDecimal 小数点精度为2
     * 例如：
     * 100分转化之后为 1.00
     * 10分转化之后为 0.10
     * 1分转化之后为 0.01
     *
     * @param cent 分
     */
    public static BigDecimal centToBigDecimalWithTwoScale(long cent) {
        return centToYuanBigDecimal(cent, 2);
    }

    /** 分转元 小数点精度为2 */
    public static BigDecimal centToBigDecimalWithTwoScale(int cent) {
        return centToYuanBigDecimal(cent, 2);
    }

    /** 分转元 String类型元 小数点精度为2 */
    public static String centToYuanWithTwoScale(long cent) {
        return centToBigDecimalWithTwoScale(cent).stripTrailingZeros().toPlainString();
    }

    /** 分转元 小数点精度为2 + 单位"元"字 */
    public static String centToYuanWithUnit(long cent) {
        return centToBigDecimalWithTwoScale(cent).stripTrailingZeros().toPlainString() + "元";
    }

    /** 分转元 小数点精度为2 BigDecimal */
    public static BigDecimal centToYuanBigDecimal(long cent, int scale) {
        return centToBigDecimal(cent, scale, YUAN);
    }

    /** 分转元 小数点精度为2 BigDecimal */
    public static BigDecimal centToYuanBigDecimal(int cent, int scale) {
        return centToBigDecimal(cent, scale, YUAN);
    }

    /**
     * Base
     * @param amount    金额 分
     * @param scale     小数点
     * @param unit      单位 元/万元
     * @return
     */
    public static BigDecimal centToBigDecimal(long amount, int scale, String unit) {
        return new BigDecimal(Long.toString(amount)).divide(new BigDecimal(unit), scale, BigDecimal.ROUND_HALF_UP);
    }

    /** 分转万元 小数点精度为4 BigDecimal */
    public static BigDecimal centToThousandBigDecimalWithTwoScale(long cent) {
        return centToThousandBigDecimal(cent, 4);
    }

    /** 分转万元 小数点精度为4 BigDecimal */
    public static BigDecimal centToThousandBigDecimal(long cent, int scale) {
        return centToBigDecimal(cent, scale, THOUSAND);
    }

    /** 分转万元 String类型元 小数点精度为4 */
    public static String centToThousandStrWithTwoScale(long cent) {
        return centToThousandBigDecimalWithTwoScale(cent).stripTrailingZeros().toPlainString();
    }

    /** 分转万元 String类型元 + 单位"万"字 */
    public static String centToThousandWithUnit(long cent) {
        return centToThousandBigDecimalWithTwoScale(cent).stripTrailingZeros().toPlainString() + "万";
    }

    /**
     * 将元转代为分
     * 例如：
     * 1.00元转化之后为100
     * 0.10元转化之后为10分
     * 0.01元转化之后为1分
     *
     * @param yuan 元
     */
    public static long bigdecimalToCent(BigDecimal yuan) {
        BigDecimal cnetDecimal = yuan.multiply(new BigDecimal(YUAN));
        return cnetDecimal.longValue();
    }

    public static long bigdecimalToCent(String yuan) {
        BigDecimal decimal = new BigDecimal(yuan);
        BigDecimal cnetDecimal = decimal.multiply(new BigDecimal(YUAN));
        return cnetDecimal.longValue();
    }

    /**
     * 将元转代为分
     *
     * @param
     * @return
     * @auther wang.sj
     */
    public static BigDecimal yuanToCent(BigDecimal yuan) {
        return yuan.multiply(new BigDecimal(YUAN));
    }

    /**
     * 元转元格式化
     * 例 如：
     * 1元转化之后为1.00元
     * 1.1元转化之后为1.10元
     * 1.01元转化之后为1.01元
     *
     * @param
     * @return
     * @auther wang.sj
     */
    public static BigDecimal formatYuan(String yuan) {
        return centToBigDecimalWithTwoScale(new BigDecimal(yuan).multiply(new BigDecimal(YUAN)).longValue());
    }

    public static String formatToThousandAmountSection(long centStart, long centEnd){
        if (centStart == centEnd){
            return centToThousandWithUnit(centEnd);
        }
        return centToThousandStrWithTwoScale(centStart) + "-" + centToThousandWithUnit(centEnd);
    }
}
