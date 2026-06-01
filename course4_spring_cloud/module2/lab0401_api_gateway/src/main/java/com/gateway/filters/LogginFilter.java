package com.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LogginFilter implements GlobalFilter{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Global Pre Filter executed");
        System.out.println("Request path: " + exchange.getRequest().getPath());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            System.out.println("Global Post Filter executed");
        }));
    }
}
