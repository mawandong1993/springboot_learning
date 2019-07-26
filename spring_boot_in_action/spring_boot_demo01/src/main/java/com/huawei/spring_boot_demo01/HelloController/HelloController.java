package com.huawei.spring_boot_demo01.HelloController;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController     //等同于同时加上了@Controller和@ResponseBody
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private GirlPropertits girlPropertits;

    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    //访问/hello或者/hi任何一个地址，都会返回一样的结果
    //@RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET)
    @RequestMapping(value = "hi",method = RequestMethod.GET)
    public String say(){

        logger.info("56484848");
        return JSON.toJSONString(girlPropertits);
    }

}