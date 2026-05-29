package com.mountain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@SpringBootApplication
@RestController
public class MountainServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MountainServiceApplication.class, args);
    }

    @RequestMapping("/")
    public String index(){
        return "This is the spring boot Mountain-service application";
    }

    @Inject
    private MountainDAO dao;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mountain get(@PathVariable("id") long id){
        return dao.get(id);
    }
}
