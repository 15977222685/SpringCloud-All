package com.hlp.eurekaconsumer.eurekaconsumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MainController2 {

    @Autowired
    DiscoveryClient client;
    @Autowired
    EurekaClient client2;
    @Autowired
    LoadBalancerClient lb;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/client6")
    public Object client6(){
        ServiceInstance instance = lb.choose("provider");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";
        System.out.println("url:"+url);
        String respStr = restTemplate.getForObject(url, String.class);
        return respStr;
    }

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/client7")
    public Object client7(){

        List<ServiceInstance> instances = discoveryClient.getInstances("provider");

        //自定义轮询算法

        //随机
        int nextInt = new Random().nextInt(instances.size());
        AtomicInteger atomicInteger = new AtomicInteger();

        //轮训
        int i = atomicInteger.getAndIncrement();
        instances.get(i % instances.size());

        //权重
        for (ServiceInstance serviceInstance : instances) {
            serviceInstance.getMetadata();//权重
        }

        ServiceInstance instance = instances.get(nextInt);

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";
        String respStr = restTemplate.getForObject(url, String.class);
        return respStr;
    }

    @GetMapping("/client8")
    public Object client8(){
        ServiceInstance instance = lb.choose("provider");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";
        String respStr = restTemplate.getForObject(url, String.class);
        System.out.println(respStr);
        return respStr;
    }

    @GetMapping("/client9")
    public Object client9(){
        String url = "http://provider/getHi";
        String respStr = restTemplate.getForObject(url, String.class);
        System.out.println(respStr);
        return respStr;
    }
}
