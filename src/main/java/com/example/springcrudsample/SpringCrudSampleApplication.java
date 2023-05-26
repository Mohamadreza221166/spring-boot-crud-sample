package com.example.springcrudsample;

import com.example.springcrudsample.config.AdditionalProperties;
import com.example.springcrudsample.config.Constants;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties({ AdditionalProperties.class })
public class SpringCrudSampleApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringCrudSampleApplication.class, args);
        SpringApplication app = new SpringApplication(SpringCrudSampleApplication.class);
        addDefaultProfile(app);
        app.run(args).getEnvironment();
    }

    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap();
        defProperties.put("spring.profiles.default", "dev");
        app.setDefaultProperties(defProperties);
    }

//    @Bean
//    pu/blic WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry
//                        .addMapping("/greeting-javaconfig")
//                        .allowedOrigins("http://localhost:8080");
//            }
//        };
//    }

}
