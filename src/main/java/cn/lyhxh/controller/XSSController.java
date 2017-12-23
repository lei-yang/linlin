package cn.lyhxh.controller;

import cn.lyhxh.model.XSS;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * XSS攻击测试
 * Created by Ran Han on 2017/12/19.
 */
@RestController
@RequestMapping("xss")
public class XSSController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "xss1", method = RequestMethod.GET)
    public XSS xss1(XSS xss) {
        logger.info("XSS : {}", JSON.toJSONStringWithDateFormat(xss, JSON.DEFFAULT_DATE_FORMAT));
        return xss;
    }

    @RequestMapping(value = "xss2", method = RequestMethod.POST)
    public XSS xss2(@RequestBody XSS xss) {
        logger.info("XSS : {}", JSON.toJSONStringWithDateFormat(xss, JSON.DEFFAULT_DATE_FORMAT));
        return xss;
    }

}
