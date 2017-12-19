package cn.lyhxh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序入口
 * Created by Ran Han on 2017/12/19.
 */
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class LinLinApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LinLinApplication.class, args);
    }

}
