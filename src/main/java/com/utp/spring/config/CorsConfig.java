package com.utp.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/login")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/api/productos/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*");
                registry.addMapping("/api/categorias/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*");

                registry.addMapping("/media/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*");

                registry.addMapping("/api/carrito/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/api/usuarios/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*");

                registry.addMapping("/api/clientes/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*");

                registry.addMapping("/api/empleados/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*");

                registry.addMapping("/api/proveedores/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*");

                registry.addMapping("/api/payment/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true);

            }
        };
    }
}
