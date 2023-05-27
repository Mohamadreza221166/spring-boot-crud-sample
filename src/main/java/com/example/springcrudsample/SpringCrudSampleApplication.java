package com.example.springcrudsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringCrudSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCrudSampleApplication.class, args);
//        SpringApplication app = new SpringApplication(SpringCrudSampleApplication.class);
//        addDefaultProfile(app);
//        app.run(args).getEnvironment();
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
