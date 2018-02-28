package cn.lyhxh.e;

import org.junit.Test;

/**
 * Created by Ran Han on 2018/2/27.
 */
public class StringTest1 {

    /*
     * 1，给定一个字符串数组。按照字典顺序进行从小到大的排序。
     * {"nba","abc","cba","zz","qq","haha"}
     *
     * 思路：
     * 1,对数组排序。可以用选择，冒泡都行。
     * 2,for嵌套和比较以及换位。
     * 3,问题：以前排的是整数，比较用的比较运算符，可是现在是字符串对象。
     *   字符串对象怎么比较呢？爽了，对象中提供了用于字符串对象比较的功能。
     *
     */
    @Test
    public void testString() {
        String[] arr = { "nba", "abc", "cba", "zz", "qq", "haha" };

        printArray(arr);

        sortString(arr);

        printArray(arr);
    }

    public static void sortString(String[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                System.out.println("i= " + arr[i]);
                System.out.println("j= " + arr[j]);
                System.out.println(arr[i].compareTo(arr[j])>0);
                System.out.println();
                if(arr[i].compareTo(arr[j])>0)//字符串比较用compareTo方法
                    swap(arr,i,j);
            }
        }
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(String[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1)
                System.out.print(arr[i] + ", ");
            else
                System.out.println(arr[i] + "]");
        }
    }

}
