package com.learning.spring_boot_swagger2.service;


import com.learning.spring_boot_swagger2.vo.User;

public interface UserService {

    User getCacheOne(String name, String password);

    void deleteCache();

}
