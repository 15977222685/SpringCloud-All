package com.hlp.controller;

import com.hlp.entity.Person;
import com.hlp.service.ConsumerApi;
import com.hlp.service.rest.RestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    ConsumerApi api;

    @Autowired
    RestService rest;

    @Value("${server.port}")
    String port;


//    @Autowired
//    MashibingApi mapi;

    @GetMapping("/alive")
    public String alive() {
        /**
         * URL 不能变
         *
         * jar
         * 文档
         */
        return api.alive();
    }

    @GetMapping("/alive2")
//    @HystrixCommand(defaultFallback = "back")
    public String alive2() {
        /**
         * URL 不能变
         *
         * jar
         * 文档
         */


        return "consumer:" + port + "-->>>>>" + rest.alive();
    }


//    @GetMapping("/vip")
//    public String vip() {
//
//        return mapi.getVip();
//    }

    @GetMapping("/map")
    public Map<Integer, String> map(Integer id) {
        System.out.println(id);
        return api.getMap(id);
    }

    @GetMapping("/map2")
    public Map<Integer, String> map2(Integer id,String name) {
        System.out.println(id);
        return api.getMap2(id,name);
    }


    @GetMapping("/map3")
    public Map<Integer, String> map3(@RequestParam Map<String, Object> map) {
		HashMap<String, Object> map1 = new HashMap<>(2);
		map1.put("id", 20000);
		map1.put("name", "花木兰");
        return api.getMap3(map1);
    }


    @GetMapping("/map4")
    public Map<Integer, String> map4(@RequestParam Map<String, Object> map) {

        System.out.println(map);
        return api.postMap(map);
    }//		System.out.println(id);
//		HashMap<String, Object> map = new HashMap<>(2);
//
//		map.put("id", id);
//		map.put("name", name);
//		syso

    @GetMapping("/postPerson")
    public Person postPerson(@RequestParam Map<String, Object> map){
        Person person = new Person();
        person.setId(Integer.parseInt(map.get("id").toString()));
        person.setName("xxoo");
        return api.postPerson(person);
    };
}
