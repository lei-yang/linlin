package cn.lyhxh.g;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Ran Han on 2018/3/5.
 */
public class WrapperTest {

    /*
     * 对一个字符串中的数值进行从小到大的排序。
     *
     * "20 78 9 -7 88 36 29"
     *
     * 思路：
     * 1，排序，我很熟。可是我只熟int。
     * 2，如何获取到这个字符串中的这些需要排序的数值？
     * 发现这个字符串中其实都是空格来对数值进行分隔的。
     * 所以就想到用字符串对象的切割方法将大串变成多个小串。
     * 3，数值最终变成小字符串，怎么变成一个int数呢？
     * 字符串-->基本类型 可以使用包装类。
     *
     *
     */
    private static final String SPACE_SEPARATOR = " ";
    
    @Test
    public void testString () {
        String numStr = "20 78 9 -7 88 36 29";

        System.out.println(numStr);
        numStr = sortStringNumber(numStr);
        System.out.println(numStr);
    }

    private String sortStringNumber(String numStr) {
        //1,将字符串变成字符串数组。
        String[] strArr = stringToArray(numStr);

        //2,将字符串数组变成int数组。

        int[] numArr = toIntArray(strArr);

        //3,对int数组排序。
        mySortArray(numArr);

        //4,将排序后的int数组变成字符串。

        return arrayToString(numArr);
    }

    private String arrayToString(int[] numArr) {
        StringBuilder sb = new StringBuilder();
        for(int x = 0; x<numArr.length; x++){
            if(x!=numArr.length-1)
                sb.append(numArr[x]+SPACE_SEPARATOR);
            else
                sb.append(numArr[x]);
        }

        return sb.toString();
    }

    private void mySortArray(int[] numArr) {
        Arrays.sort(numArr);
    }

    private int[] toIntArray(String[] strArr) {
        int[] arr = new int[strArr.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }

        return arr;
    }

    private String[] stringToArray(String numStr) {
        return numStr.split(SPACE_SEPARATOR);
    }

}
