package com.hlp.back;

import com.hlp.entity.Person;
import com.hlp.service.ConsumerApi;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserProviderBack implements ConsumerApi {

    @Override
    public String alive() {
        return "降级了";
    }

    @Override
    public String getById(Integer id) {
        return null;
    }

    @Override
    public Person postPerson(Person person) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap(Integer id) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap2(Integer id, String name) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap3(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<Integer, String> postMap(Map<String, Object> map) {
        return null;
    }
}
