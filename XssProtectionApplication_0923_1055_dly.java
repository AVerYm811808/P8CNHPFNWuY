// 代码生成时间: 2025-09-23 10:55:17
package com.example.xssprotection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
public class XssProtectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(XssProtectionApplication.class, args);
    }
}

@RestController
class XssController {

    @GetMapping("/xss")
    public String xssAttack(String userInput) {
        // Simple logic to demonstrate XSS protection
        String safeInput = sanitizeInput(userInput);
        return "Received input: " + safeInput;
    }

    // This method should be replaced with a comprehensive XSS sanitization library or implementation
    private String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        // Naive implementation for demonstration purposes.
        // In a real application, use a library like OWASP Java HTML Sanitizer.
        return input.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(""", "&quot;");
    }
}

class XssFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Add your filter logic here
        filterChain.doFilter(request, response);
    }
}

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        // Log the exception, or handle it as needed
        return "error"; // Return a custom error message or view
    }
}