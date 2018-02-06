package cn.lyhxh;

import org.junit.Test;

/**
 * Created by Ran Han on 2018/2/6.
 */
public class SwitchNum {

    @Test
    public void testTemp() {
        int a = 1, b=5;
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a = " + a + "; b = " + b);
    }

    @Test
    public void testNum() {
        int a = 1, b = 5;
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a = " + a + "; b = " + b);
    }

    @Test
    public void testN() {
        int a = 3, b = 5;
        a = a ^ b; //a = 3 ^ 5;
        b = a ^ b; //b = (3^5)^5; b = 3;
        a = a ^ b; //a = (3^5)^3; a = 5;
        System.out.println("a = " + a + "; b = " + b);
    }

}
