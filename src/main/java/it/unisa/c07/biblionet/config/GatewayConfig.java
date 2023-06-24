package it.unisa.c07.biblionet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/autenticazione/login")
                        .uri("http://localhost:8090/")
                )
                .route(r -> r.path("/club-del-libro/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8081/")
                )
                .route(r -> r.path("/lettore/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8081/")
                )
                .route(r -> r.path("/esperto/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8081/")
                )
                .route(r -> r.path("/biblioteca/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8082/")
                )
                .route(r -> r.path("/prenotazione-libri/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8082/")
                )
                .build();
    }

}
