package cn.lyhxh.e;

import org.junit.Test;

/**
 * Created by Ran Han on 2018/2/28.
 */
public class StringTest2 {

    @Test
    public void testString() {
        String s1 = "hello";
        String s2 = "java";

        show(s1,s2);

        System.out.println(s1+"...."+s2);// hello java
    }

    @Test
    public void test2String() {
        String s1 = "hello";
        String s2 = "java";

        s1 = show(s1,s2);

        System.out.println(s1+"...."+s2);// hello java
    }

    private String show(String s1, String s2) {
        s2 = s2.replace('a','o');
        System.out.println("s2 = " + s2);// s2 = jovo
        s1 = s2;
        return s1;
    }
}
