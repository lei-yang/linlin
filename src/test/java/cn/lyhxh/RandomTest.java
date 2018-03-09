package cn.lyhxh;

import org.apache.commons.codec.binary.Base32;
import org.junit.Test;

import java.security.SecureRandom;

/**
 * Created by Ran Han on 2018/3/5.
 */
public class RandomTest {

    @Test
    public void testRandom() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();

        String s = base32.encodeAsString(bytes);
        System.out.println(s);

//        String str = s.replace("=", "");
        String str = "ITX7FEU";
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        System.out.println(str);
        if (8 <= sb.length()) {
            String rs = sb.substring(0, 8);
            System.out.println(rs);
        } else {
            while (8 > sb.length()) {
                sb.append("0");
            }
        }
        System.out.println(sb.toString());
        System.out.println(sb.length());
    }
}
