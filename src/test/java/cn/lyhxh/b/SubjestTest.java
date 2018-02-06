package cn.lyhxh.b;

import org.junit.Test;

/**
 * Created by Ran Han on 2018/2/6.
 */
public class SubjestTest {

    @Test
    public void test1() {
        int x = 1,y=1;

        if(x++==2 & ++y==2) {
            x =7;
        }

        System.out.println("x="+x+",y="+y);//x=2,y=2
    }

    @Test
    public void test2() {
        int x = 1,y = 1;

        if(x++==2 && ++y==2) {
            x =7;
        }

        System.out.println("x="+x+",y="+y);//x=2,y=1
    }

    @Test
    public void test3() {
        int x = 1,y = 1;

        if(x++==1 | ++y==1) {
            x =7;
        }

        System.out.println("x="+x+",y="+y);//x=7,y=2
    }

    @Test
    public void test4() {
        int x = 1,y = 1;

        if(x++==1 || ++y==1) {
            x =7;
        }

        System.out.println("x="+x+",y="+y);//x=7,y=1
    }

    @Test
    public void test5() {
        boolean b = true;

        if(b==false)  //如果写成if(b=false)有结果吗？如果有，结果是？
            System.out.println("a");
        else if(b)
            System.out.println("b");
        else if(!b)
            System.out.println("c");
        else
            System.out.println("d"); // b
    }

    @Test
    public void test6() {
        int x = 2,y=3;

        switch(x) {
            default:
                y++;
            case 3:
                y++;
            case 4:
                y++;
        }

        System.out.println("y="+y); // y=6
    }

    @Test
    public void test7() {
        for(int x=1; x<=5; x++) {
            for(int y=x; y<=5; y++) {
                System.out.print("*");
            }
            System.out.println();
        }
        /*

		*****
		****
		***
		**
		*

		*/
    }
}
