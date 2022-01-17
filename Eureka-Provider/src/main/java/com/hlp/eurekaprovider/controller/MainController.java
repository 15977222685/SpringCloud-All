package com.hlp.eurekaprovider.controller;

import com.hlp.eurekaprovider.entity.Person;
import com.hlp.eurekaprovider.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

@RestController
public class MainController {

    @Value("${server.port}")
    String port;

    @GetMapping("/getHi")
    public String getHi(){
        return "Hi，我的port：" + port;
    }


    @GetMapping("/getMap")
    public Map<String,String> getMap(){
        return Collections.singletonMap("id","100");
    }

    @GetMapping("/getObj")
    public Person getObj(){
        Person person = new Person(100,"xiao6");
        return person;
    }

    @GetMapping("/getObj2")
    public Person getObj2(String name){
        Person person = new Person(100,name);
        return person;
    }

    @PostMapping("/postParam")
    @ResponseBody
    public Person postParam(@RequestBody String name) {
        System.out.println("name:" + name);
        Person person = new Person();
        person.setId(100);
        person.setName("xiaoming" + name);
        return person;
    }


    @PostMapping("/postLocation")
    public URI postParams(@RequestBody Person person, HttpServletResponse response) throws URISyntaxException {
        URI uri = new URI("https://www.baidu.com/s?wd="+person.getName().trim());
        response.addHeader("Location",uri.toString());
        return uri;
    }
    @Autowired
    HealthStatusService healthStatusService;

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status){
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus();
    }
}
