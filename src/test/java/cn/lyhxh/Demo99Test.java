package cn.lyhxh;

import org.junit.Test;

/**
 * 99乘法表
 *
 * Created by Ran Han on 2018/2/6.
 */
public class Demo99Test {

    @Test
    public void test99() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " * " + i + " = " + i*j + " ");
            }
            System.out.println();
        }
    }
}
