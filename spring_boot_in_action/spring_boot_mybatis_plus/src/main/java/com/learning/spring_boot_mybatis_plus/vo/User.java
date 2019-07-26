package com.learning.spring_boot_mybatis_plus.vo;

import lombok.Data;

/**
 * @author mawandong
 * @date 2019/7/27 0:25
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
