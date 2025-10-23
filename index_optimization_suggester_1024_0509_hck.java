// 代码生成时间: 2025-10-24 05:09:17
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class IndexOptimizationSuggester {

    public static void main(String[] args) {
        SpringApplication.run(IndexOptimizationSuggester.class, args);
    }

    @GetMapping("/suggest")
    public ResponseEntity<Map<String, String>> suggestIndex(@RequestParam String tableName) {
        Map<String, String> suggestions = new HashMap<>();
        suggestions.put("suggestion", "Consider creating an index on the 'id' column of the 'tableName' table for better performance.");
        return ResponseEntity.ok(suggestions);
    }

    @ControllerAdvice
    static class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex) {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("errorMessage", ex.getMessage());
            return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
