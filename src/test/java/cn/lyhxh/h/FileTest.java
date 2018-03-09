package cn.lyhxh.h;

import org.junit.Test;

import java.io.File;

/**
 * Created by Ran Han on 2018/3/9.
 */
public class FileTest {

    /*
     * 需求：对指定目录进行所有内容的列出（包含子目录中的内容）
     * 也可以理解为 深度遍历。
     *
     *
     */
    @Test
    public void testFile() {
        File dir = new File("f:\\win");

        listAll(dir,0);
    }

    public static void listAll(File dir,int level) {


        System.out.println(getSpace(level)+dir.getName());
        //获取指定目录下当前的所有文件夹或者文件对象

        level++;
        File[] files = dir.listFiles();

        for(int x=0; x<files.length; x++){

            if(files[x].isDirectory()){
                listAll(files[x],level);
            }
            else
                System.out.println(getSpace(level)+files[x].getName());
        }
    }

    private static String getSpace(int level) {

        StringBuilder sb = new StringBuilder();

        sb.append("|--");
        for(int x=0; x<level; x++){
            sb.insert(0,"|  ");
        }

        return sb.toString();
    }

}
