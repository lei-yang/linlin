package cn.lyhxh.a;

import org.junit.Test;

/**
 * Created by Ran Han on 2018/2/6.
 */
public class SwitchTest {

    @Test
    public void testSwitch() {
        int week = 1;
        switch(week) {
            default:
                break;
            case 1:
                System.out.println("星期一");
                break;
            case 2:
                System.out.println("星期二");
        }
    }

}
