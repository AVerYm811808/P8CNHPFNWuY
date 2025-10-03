// 代码生成时间: 2025-10-04 01:52:20
package com.example.actuarialmodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ActuarialModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActuarialModelApplication.class, args);
    }

    // REST API to calculate insurance premium
    @GetMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculatePremium(@RequestParam(required = false) Double age, @RequestParam(required = false) Double riskFactor) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (age == null || riskFactor == null) {
                result.put("error", "Age and risk factor are required");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }

            // Here you would implement your actuarial model logic to calculate the premium
            double premium = calculateActuarialPremium(age, riskFactor);
            result.put("premium", premium);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    // Dummy actuarial model calculation
    private double calculateActuarialPremium(double age, double riskFactor) {
        // The actual implementation would depend on the actuarial model
        return age * riskFactor;
    }

    // Exception handler for unknown exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        Map<String, String> result = new HashMap<>();
        result.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
}
