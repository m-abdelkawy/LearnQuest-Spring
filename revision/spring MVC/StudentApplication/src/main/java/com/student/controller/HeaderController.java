package com.student.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/myHeader")
public class HeaderController {

    @GetMapping
    public String getHeader(HttpServletRequest request) {
        return "Hello " + request.getHeader("user-agent");
    }

    @GetMapping("/mandatory")
    public String getHeader(@RequestHeader("user-agent") String agent) {
        return "Mandatory: Hello " + agent;
    }

    @GetMapping("/optional")
    public String getHeader(@RequestHeader("user-agent") Optional<String> optional) {
        return "Optional: Hello " + optional.orElse("no agent");
    }
}
