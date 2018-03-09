package cn.lyhxh.h;

import org.junit.Test;

import java.io.File;

/**
 * Created by Ran Han on 2018/3/9.
 */
public class RemoveDirTest {


    /*
     * 删除一个带内容的目录。
     *
     * 原理：必须从最里面往外删。
     * 需要进行深度遍历。
     *
     */
    @Test
    public void testFile() {
        File dir  = new File("e:\\demodir");
//		dir.delete();
        removeDir(dir);
    }

    public static void removeDir(File dir) {

        File[] files = dir.listFiles();

        for(File file : files){

            if(file.isDirectory()){
                removeDir(file);
            }else{
                System.out.println(file+":"+file.delete());
            }
        }
        System.out.println(dir+":"+dir.delete());
    }

}
