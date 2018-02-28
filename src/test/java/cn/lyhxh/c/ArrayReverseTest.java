package cn.lyhxh.c;

import org.junit.Test;

/**
 * Created by Ran Han on 2018/2/7.
 */
public class ArrayReverseTest {

    @Test
    public void testReserse() {
        int[] arr = {4,1,8,7,3,8,2};
        printArray(arr);
        reverseArray(arr);
        printArray(arr);
    }

    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int x=0; x<arr.length; x++) {
            if (x!=arr.length-1) {
                System.out.print(arr[x] + ", ");
            } else {
                System.out.println(arr[x] + "]");
            }
        }
    }

    public static void reverseArray(int[] arr) {
        for(int start=0,end=arr.length-1; start<end; start++,end--) {
            swap(arr,start,end);
        }
    }

    public static void swap(int[] arr,int a,int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    @Test
    public void testArray2() {
        int[] arr = new int[2];
        System.out.println(arr[0]);
        show(arr);
        System.out.println(arr[0]);
    }

    public static void show(int[] arr) {
        arr[0]++;
    }

}
