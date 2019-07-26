package com.learning.spring_boot_swagger2.controller;

import com.alibaba.fastjson.JSON;
import com.learning.spring_boot_swagger2.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author mawandong
 * @date 2018/10/30 20:01
 */
@Controller
@RequestMapping("user")
@Api(value = "用户接口", tags = "用户的增删改查")
@Log
public class UserController {


    @GetMapping("/getOne")
    @ResponseBody
    @ApiOperation(value = "查询一个用户", notes = "查询一个用户xxx")
    public User getOne(String name, String password, HttpSession session) {
        User user = new User();
        user.setId(this.getLong());
        user.setName(name);
        user.setPassword(password);

        log.info(JSON.toJSONString(user));

        return user;
    }

    @GetMapping("/getList")
    @ResponseBody
    @ApiOperation(value = "查询多个用户", notes = "查询多个用户xxx")
    public List<User> getList(String name, String nick, String password, String confirm) {

        List<User> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            User user = new User();
            user.setId(this.getLong());
            user.setName(name);
            user.setPassword(password);
            list.add(user);

        }

        log.info(JSON.toJSONString(list));

        return list;
    }

    @PostMapping("/addOne")
    @ResponseBody
    @ApiOperation(value = "增加一个用户", notes = "增加一个用户xxx")
    public User addOne(User user) {
        user.setId(this.getLong());

        log.info(JSON.toJSONString(user));

        return user;
    }

    private Long getLong() {

        Long l = ThreadLocalRandom.current().nextLong();
        return l;
    }


}
