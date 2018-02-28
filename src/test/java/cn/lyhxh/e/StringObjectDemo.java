package cn.lyhxh.e;

import org.junit.Test;

/**
 * Created by Ran Han on 2018/2/27.
 */
public class StringObjectDemo {

    @Test
    public void testString() {
        // String s1 = "abc";
		// String s2 = "abc";

        // intern():对字符串池进行操作的

        String s1 = new String("abc");
        String s2 = s1.intern();

        System.out.println(s1==s2);
    }

}
