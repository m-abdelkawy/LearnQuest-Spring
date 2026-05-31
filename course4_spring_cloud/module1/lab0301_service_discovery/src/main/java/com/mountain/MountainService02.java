package com.mountain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@SpringBootApplication
@RestController
public class MountainService02 {
    public static void main(String[] args) {
        SpringApplication.run(MountainService02.class, args);
    }

    @RequestMapping("/")
    public String index() {
        return "This is the spring boot Service02 application";
    }

    @Inject
    private FirstAscentDAO dao;

    @GetMapping(value = "/{id}")
    public String get(@PathVariable("id") long id) {
        return dao.get(id);
    }
}
