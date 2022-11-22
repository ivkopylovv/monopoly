package com.game.monopoly.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsWebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedOrigins("/*")
                .allowedOrigins("https://thebestmonopoly.herokuapp.com")
                .allowedOrigins("http://thebestmonopoly.herokuapp.com")
                .allowedOrigins("/**")
                .allowCredentials(true);
    }
}
