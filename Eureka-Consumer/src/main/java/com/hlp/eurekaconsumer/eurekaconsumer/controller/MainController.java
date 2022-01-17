package com.hlp.eurekaconsumer.eurekaconsumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import javafx.fxml.LoadException;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    DiscoveryClient client;
    @Autowired
    EurekaClient client2;
    @Autowired
    LoadBalancerClient lb;

    @GetMapping("/getHi")
    public String getHi(){
        return "Hi";
    }

    @GetMapping("/client")
    public String client(){
        List<String> services = client.getServices();
        for (String str : services){
            System.out.println(str);
        }
        return "Hi";
    }

    @GetMapping("/client2")
    public Object client2(){
        return client.getInstances("provider");
    }

    @GetMapping("/client3")
    public Object client3(){
        List<ServiceInstance> instances = client.getInstances("provider");
        for (ServiceInstance ins : instances){
            System.out.println(ToStringBuilder.reflectionToString(ins));
        }
        return "xxoo";
    }

    @GetMapping("/client4")
    public Object client4(){
        //List<InstanceInfo> instances = client2.getInstancesById("localhost:provider:80");

        List<InstanceInfo> instances = client2.getInstancesByVipAddress("provider", false);

        for (InstanceInfo ins : instances){
            System.out.println(ToStringBuilder.reflectionToString(ins));
        }
        if (instances.size()>0){
            InstanceInfo instanceInfo = instances.get(0);
            if (instanceInfo.getStatus() == InstanceInfo.InstanceStatus.UP){
                String url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/getHi";
                System.out.println("url:"+url);
                RestTemplate restTemplate = new RestTemplate();
                String respStr = restTemplate.getForObject(url, String.class);
                System.out.println("respStr:"+respStr);
            }
        }
        return "xxoo";
    }

    @GetMapping("/client5")
    public Object client5(){
        ServiceInstance instance = lb.choose("provider");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";
        System.out.println("url:"+url);
        RestTemplate restTemplate = new RestTemplate();
        String respStr = restTemplate.getForObject(url, String.class);
        System.out.println("respStr:"+respStr);
        return "xxoo";
    }
}
