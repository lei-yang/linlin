package amount;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * 金额转换测试类
 *
 * @author leiyang
 * @since 2019/5/31 9:08
 */
public class AmountTest {

    @Test
    public void testAmount() {
        Long amount = 1737000L;
        BigDecimal decimal = AmountUtil.centToBigDecimalWithTwoScale(amount);
        System.out.println(decimal.toString());
    }
}
