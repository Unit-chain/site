package com.unitserver.unit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Controller
public class Router {

    @GetMapping(value = { "/", "/{x:[\\w\\-]+}", "/{x:^(?!api$).*$}/*/{y:[\\w\\-]+}", "/error"})
    public String index() {
        return "index";
    }

//    @Bean
//    public RouterFunction<ServerResponse> htmlRouter(
//            @Value("classpath:/public/index.html") Resource html) {
//        return route(GET("/"), request
//                -> ok().contentType(MediaType.TEXT_HTML).bodyValue(html)
//        );
//    }
//    @Bean
//    public RouterFunction<ServerResponse> imgRouter() {
//        return RouterFunctions
//                .resources("/img/**", new ClassPathResource("img/"));
//    }
//    @Bean
//    public RouterFunction<ServerResponse> staticRouter() {
//        return RouterFunctions
//                .resources("/static/**", new ClassPathResource("static/"));
//    }
//    @Bean
//    public RouterFunction<ServerResponse> manifestRouter() {
//        return RouterFunctions
//                .resources("templates/manifest.json", new ClassPathResource("templates/manifest.json"));
//    }
}
