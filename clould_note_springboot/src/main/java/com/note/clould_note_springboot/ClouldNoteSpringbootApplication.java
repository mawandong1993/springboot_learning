package com.note.clould_note_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.note.clould_note_springboot.dao")
public class ClouldNoteSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClouldNoteSpringbootApplication.class, args);
    }
}
