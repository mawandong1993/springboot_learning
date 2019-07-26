package com.learning.spring_boot_mybatis_plus.controller;

import com.alibaba.fastjson.JSON;
import com.learning.spring_boot_mybatis_plus.dao.UserMapper;
import com.learning.spring_boot_mybatis_plus.vo.User;
import com.learning.spring_boot_mybatis_plus.vo.User_old;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
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
@ResponseBody
@RequestMapping("user")
@Api(value = "用户接口", tags = "用户的增删改查")
@Log
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test1")
    @ApiOperation(value = "查询一个用户", notes = "查询一个用户xxx")
    public List<User> test1() {

        List<User> list = userMapper.selectList(null);

        log.info(JSON.toJSONString(list));
        return list;
    }


    @GetMapping("/getOne")
    @ApiOperation(value = "查询一个用户", notes = "查询一个用户xxx")
    public User_old getOne(String name, String password, HttpSession session) {
        User_old userOld = new User_old();
        userOld.setId(this.getLong());
        userOld.setName(name);
        userOld.setPassword(password);

        log.info(JSON.toJSONString(userOld));
        return userOld;
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询多个用户", notes = "查询多个用户xxx")
    public List<User_old> getList(String name, String nick, String password, String confirm) {

        List<User_old> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User_old userOld = new User_old();
            userOld.setId(this.getLong());
            userOld.setName(name);
            userOld.setPassword(password);
            list.add(userOld);
        }

        log.info(JSON.toJSONString(list));
        return list;
    }

    @PostMapping("/addOne")
    @ApiOperation(value = "增加一个用户", notes = "增加一个用户xxx")
    public User_old addOne(User_old userOld) {
        userOld.setId(this.getLong());

        log.info(JSON.toJSONString(userOld));
        return userOld;
    }

    private Long getLong() {
        Long l = ThreadLocalRandom.current().nextLong();
        return l;
    }


}
