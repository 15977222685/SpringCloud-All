package com.hlp.controller;

import com.hlp.api.UserApi;
import com.hlp.entity.Person;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class UserController implements UserApi {

    @Value("${server.port}")
    String port;


    private AtomicInteger count = new AtomicInteger();

    @Override
    public String alive() {

        System.out.println("准备睡");

//        try {
//            System.out.println("准备睡");
//
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

//        int i = count.getAndIncrement();
        int i = 1/1;
        System.out.println(port+"好的：====第：" + i + "次调用");
        return "provider:port:" + port;
    }

    @Override
    public String getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @GetMapping("/getMap")
    public Map<Integer, String> getMap(Integer id) {
        // TODO Auto-generated method stub
        System.out.println(id);
        return Collections.singletonMap(id, "mmeme");
    }
    @GetMapping("/getMap2")
    public Map<Integer, String> getMap2(Integer id,String name) {
        // TODO Auto-generated method stub
        System.out.println(id);
        return Collections.singletonMap(id, name);
    }

    @GetMapping("/getMap3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }


    @PostMapping("/postMap")
    public Map<Integer, String> postMap(@RequestBody Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }

    @Override
    public Person postPerson(Person person) {
        System.out.println(ToStringBuilder.reflectionToString(person));
        return person;
    }
}
