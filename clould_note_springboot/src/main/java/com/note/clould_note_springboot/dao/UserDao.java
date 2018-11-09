package com.note.clould_note_springboot.dao;


import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author mawandong
 * @date 2018/11/9 23:05
 */
public interface UserDao {

    List<Map<String,Object>> getTest();

    @Select("SELECT now()")
    Date SelectNow();

}
