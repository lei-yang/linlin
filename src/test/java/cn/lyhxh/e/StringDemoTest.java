package cn.lyhxh.e;

import org.junit.Test;

/**
 * Created by Ran Han on 2018/2/27.
 */
public class StringDemoTest {

    @Test
    public void testString() {
        /*
		 * 将字节数组或者字符数组转成字符串可以通过String类的构造函数完成。
		 */
        stringConstructorDemo();

        stringConstructorDemo2();
    }

    public static void stringConstructorDemo() {
        String s = new String();//等效于String s = "";  不等效String s = null;

        byte[] arr = {97, 65, 98, 66, 67, 68, 69};

        String s1 = new String(arr);
        System.out.println("s1="+s1);

    }

    private static void stringConstructorDemo2() {
        char[] arr = {'w','a','p','q','x'};
        String str = new String(arr);
        System.out.println("str=" + str);

        String s = new String(arr,1,3);
        System.out.println("s="+s);
    }

}
