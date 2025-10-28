// 代码生成时间: 2025-10-28 17:05:09
package com.example.homeworkmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HomeworkManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkManagementApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configureHandlerExceptionResolvers(org.springframework.web.servlet.HandlerExceptionResolverConfigurer configurer) {
                configurer
                    .setHandlerExceptionResolver(new CustomExceptionHandler());
            }
        };
    }
}

class CustomExceptionHandler implements org.springframework.web.servlet.HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // Implement custom exception handling logic here
        return new ModelAndView("error", "message", "An error occurred: " + ex.getMessage());
    }
}
