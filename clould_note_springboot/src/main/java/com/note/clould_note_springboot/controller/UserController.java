package com.note.clould_note_springboot.controller;

import com.note.clould_note_springboot.dao.UserDao;
import com.note.clould_note_springboot.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author mawandong
 * @date 2018/10/30 20:01
 */
@Controller
@RequestMapping("user")
@Api(value = "用户接口", tags = "用户的增删改查")
public class UserController {


    @GetMapping("/getOne")
    @ResponseBody
    @ApiOperation(value = "查询一个用户", notes = "查询一个用户xxx")
    public User getOne(String name, String password, HttpSession session) {
        User user = new User();
        user.setId(this.getLong());
        user.setName(name);
        user.setPassword(password);

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
        return list;
    }

    @PostMapping("/addOne")
    @ResponseBody
    @ApiOperation(value = "增加一个用户", notes = "增加一个用户xxx")
    public User addOne(User user) {
        user.setId(this.getLong());
        return user;
    }

    private Long getLong() {

        Long l = ThreadLocalRandom.current().nextLong();
        return l;
    }

    @Autowired
    private UserDao userDao;


    @PostMapping("/test")
    @ResponseBody
    public Map<String, Object> test() {

       Date date= userDao.SelectNow();


        List<Map<String, Object>> list = userDao.getTest();
        Map<String, Object> result = new HashMap<>();
        result.put("data", list);
        return result;
    }


}
