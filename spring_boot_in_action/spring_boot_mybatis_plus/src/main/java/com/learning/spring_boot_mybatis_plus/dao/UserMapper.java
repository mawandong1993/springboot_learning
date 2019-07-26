package com.learning.spring_boot_mybatis_plus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learning.spring_boot_mybatis_plus.vo.User;
import org.springframework.stereotype.Repository;

/**
 * @author mawandong
 * @date 2019/7/27 0:25
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
