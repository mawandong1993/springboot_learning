package com.huawei.spring_boot_demo01.HelloController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GrilController {

    @Autowired
    private GrilRepository grilRepository;

    @GetMapping(value = "/grils")
    public List<Gril> grilList(){
        return grilRepository.findAll();
    }

    @PostMapping(value = "/grils")
    public Gril grilAdd(@RequestParam("cupSize") String cupSize,
                         @RequestParam("age") Integer age){
        Gril gril=new Gril();
        gril.setCupSize(cupSize);
        gril.setAge(age);
        return grilRepository.save(gril);
    }

    @GetMapping(value = "/grils/{id}")
    public Gril getGril(@PathVariable("id") Integer id){
        return grilRepository.findOne(id);
    }

    @PutMapping(value = "/grilUpdate/{id}")
    public Gril GrilUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age){
        Gril gril = new Gril();
        gril.setId(id);
        gril.setCupSize(cupSize);
        gril.setAge(age);
        return grilRepository.save(gril);
    }


    @DeleteMapping(value = "/grils/{id}")
    public void grilDelete(@PathVariable("id") Integer id){
        grilRepository.delete(id);
    }

}
