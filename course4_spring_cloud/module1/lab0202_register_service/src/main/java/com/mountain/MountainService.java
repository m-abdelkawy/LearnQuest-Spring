package com.mountain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.Optional;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class MountainService {
    public static void main(String[] args) {
        SpringApplication.run(MountainService.class, args);
    }

    @RequestMapping("/")
    public String index(){
        return "This is the spring boot Mountain-service application";
    }

    @Inject
    private MountainDAO dao;

//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Mountain> get(@PathVariable("id") long id){
//        return dao.get(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mountain> get(@PathVariable("id") long id){
        Optional<Mountain> mountainOptional = dao.get(id);
        if(mountainOptional.isPresent()){
            String url = "http://ascent-service/{id}";
            String str = this.restTemplate().getForObject(url, String.class, id);
            mountainOptional.get().setFirstAscent(str);
        }
        return mountainOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
