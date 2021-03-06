package cn.lyhxh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 程序入口
 * Created by Ran Han on 2017/12/19.
 */
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@ServletComponentScan
@EnableSwagger2
public class LinLinApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LinLinApplication.class, args);
    }

}
