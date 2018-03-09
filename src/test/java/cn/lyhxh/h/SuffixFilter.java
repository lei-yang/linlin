package cn.lyhxh.h;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Ran Han on 2018/3/9.
 */
public class SuffixFilter implements FilenameFilter {

    private String suffix;

    public SuffixFilter(String suffix) {
        super();
        this.suffix = suffix;
    }

    @Override
    public boolean accept(File dir, String name) {

        return name.endsWith(suffix);
    }


}
