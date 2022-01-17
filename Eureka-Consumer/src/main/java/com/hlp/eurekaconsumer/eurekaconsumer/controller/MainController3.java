package com.hlp.eurekaconsumer.eurekaconsumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlp.eurekaprovider.entity.Person;
import com.netflix.discovery.EurekaClient;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MainController3 {

    @Autowired
    DiscoveryClient client;
    @Autowired
    EurekaClient client2;
    @Autowired
    LoadBalancerClient lb;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/client10")
    public Object client10(){
        String url = "http://provider/getHi";
        String respStr = restTemplate.getForObject(url, String.class);
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        System.out.println("entity:"+entity);
        System.out.println(respStr);
        return respStr;
    }

    @GetMapping("/client11")
    public Object client11(){
        String url = "http://provider/getMap";
        Map<String,String> map = restTemplate.getForObject(url, Map.class);
        System.out.println(map);
        return map;
    }

    @GetMapping("/client12")
    public Object client12(){
        String url = "http://provider/getObj";
        Person object = restTemplate.getForObject(url, Person.class);
        return object;
    }

    @GetMapping("/client13")
    public Object client13(){
        String url = "http://provider/getObj2?name={1}";
        Person object = restTemplate.getForObject(url, Person.class,"maxiaoliu666");
        return object;
    }

    @GetMapping("/client14")
    public Object client14(){
        String url = "http://provider/getObj2?name={name}";
        Map<String, String> map = Collections.singletonMap("name", "xiao666666");
        Person object = restTemplate.getForObject(url, Person.class,map);
        return object;
    }

    @GetMapping("/client15")
    public Object client15(){
        String url = "http://provider/postParam";
        Map<String, String> map = Collections.singletonMap("name", "1111");
        ResponseEntity<Person> entity = restTemplate.postForEntity(url, map, Person.class);
        return entity;
    }

    @GetMapping("/client16")
    public Object client16(HttpServletResponse response) throws IOException {
        String url = "http://provider/postLocation";
        Map<String, String> map = Collections.singletonMap("name","memeda");
        URI location = restTemplate.postForLocation(url, map, Person.class);
        response.sendRedirect(location.toURL().toString());
        return null;
    }
}
