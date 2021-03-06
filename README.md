## Spring Boot 项目整理
- 环境基础
   - jdk 1.8
   - maven 3.5.2
- XSS
> 跨站脚本工具（cross 斯特scripting），为不和层叠样式表（cascading style sheets,CSS）的缩写混淆，
> 故将跨站脚本攻击缩写为XSS。恶意攻击者往web页面里插入恶意scriptScript代码，当用户浏览该页之时，
> 嵌入其中Web里面的Script代码会被执行，从而达到恶意攻击用户的目的。防止XSS攻击简单的预防就是对Request
> 请求中的一些参数去掉一些比较敏感的脚本命令。
>
> 原本是打算通过SpringMVC的HandlerInterceptor机制来实现的，通过获取request然后对request中的参数
> 进行修改，结果虽然值修改了，但在Controller中获取的数值还是没有修改的。没办法就是要Filter来完成。
> 简单来说就是创建一个新的HttpRequest类XssHttpServletRequestWrapper，然后重写一些get方法（获取
> 参数时对参数进行XSS判断预防）。

-流程梳理

包装request->创建过滤器->添加过滤器

1. 创建包装request的类 `XssHttpServletRequestWrapper`

```
package cn.lyhxh.config;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by Ran Han on 2017/12/22.
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values==null)  {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value != null) {
            return cleanXSS(value);
        }
        return null;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)
            return null;
        return cleanXSS(value);
    }

    private static String cleanXSS(String value) {
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("%3C", "&lt;").replaceAll("%3E", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("%28", "&#40;").replaceAll("%29", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(inputHandlers(super.getInputStream ()).getBytes ());

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) { }
        };
    }

    public   String inputHandlers(ServletInputStream servletInputStream){
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(servletInputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (servletInputStream != null) {
                try {
                    servletInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  cleanXSS(sb.toString ());
    }

}

```

**注意：**
getInputStream()方法的流处理，注解方式获取数据貌似是根据这个流取得的数据。
因为super.getInputStream()流只允许读取一次，所以在getInputStream()方法中
处理完流数据后返回了一个新的ServletInputStream。另外替换方法里的替换规则，
也可以根据实际业务需要进行调整。

2. 创建过滤器 `MyXssFilter`

```
package cn.lyhxh.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *  * 使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * Created by Ran Han on 2017/12/19.
 */
@WebFilter(filterName="myXssFilter", urlPatterns="/*")
public class MyXssFilter implements Filter {

    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行过滤操作");

        filterChain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest)servletRequest), servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
        this.filterConfig = null;
    }

}

```

3. 添加过滤器
   - 方式1(使用注解)
       - 在程序入口添加注解 `@ServletComponentScan`
       - 在过滤器上添加注解 `@WebFilter(filterName="myXssFilter", urlPatterns="/*")`
   
   - 方式2(使用Java Bean)
   
   ```
   public class WebInitializer implements WebApplicationInitializer {
       @Override
       public void onStartup(ServletContext servletContext) throws ServletException {
           servletContext.addListener(RequestContextListener.class);
   
           FilterRegistration.Dynamic XssfilterRegistration = servletContext.addFilter("XssSqlFilter",XssFilter.class);
           XssfilterRegistration.addMappingForUrlPatterns(EnumSet.of(
                   DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");
   
       }
   }
   ```
   
   由于前面提到的本项目采用的是注入方式，虽然是web项目但并没有web.xml文件所以添加过滤器是实现了WebApplicationInitializer类的方法onStartup(),当然也可以添加拦截器。
   
- swagger
> 1.文档需要更新的时候，需要再次发送一份给前端，也就是文档更新交流不及时。
> 2.接口返回结果不明确
> 3.不能直接在线测试接口，通常需要使用工具，比如postman
> 4.接口文档太多，不好管理
>
> Swagger也就是为了解决这个问题，当然也不能说Swagger就一定是完美的，当然也有缺点，最明显的就是代码移入性比较强。

1. 添加依赖
```
<!--swagger-->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>${swagger.version}</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>${swagger.version}</version>
</dependency>
```
2. Swagger配置类
```
package cn.lyhxh.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by Ran Han on 2018/3/4.
 */
@Configurable
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.lyhxh.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restfun风格，http://blog.csdn.net/saytime")
                .termsOfServiceUrl("http://blog.csdn.net/saytime")
                .version("1.0")
                .build();
    }

}

```
3. 启动SpringBoot项目，访问 http://localhost:8080/swagger-ui.html
4. Swagger注解
```
@Api：修饰整个类，描述Controller的作用
@ApiOperation：描述一个类的一个方法，或者说一个接口
@ApiParam：单个参数描述
@ApiModel：用对象来接收参数
@ApiProperty：用对象接收参数时，描述对象的一个字段
@ApiResponse：HTTP响应其中1个描述
@ApiResponses：HTTP响应整体描述
@ApiIgnore：使用该注解忽略这个API
@ApiError ：发生错误返回的信息
@ApiImplicitParam：一个请求参数
@ApiImplicitParams：多个请求参数
```