package com.learning.spring_boot_swagger2.service.impl;

import com.alibaba.fastjson.JSON;
import com.learning.spring_boot_swagger2.service.UserService;
import com.learning.spring_boot_swagger2.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author mawandong
 * @date 2019/10/20 15:36
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    @Cacheable(value = "getCacheOne")
    public User getCacheOne(String name, String password) {
        User user = new User();
        user.setId(this.getLong());
        user.setName(name);
        user.setPassword(password);

        log.info(JSON.toJSONString(user));

        return user;
    }


    private Long getLong() {
        Long l = ThreadLocalRandom.current().nextLong();
        return l;
    }

    @Override
    @CacheEvict(value = "getCacheOne",allEntries = true)
    public void deleteCache() {
        log.info("deleteCache");

    }
}
