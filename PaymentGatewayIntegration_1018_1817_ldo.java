// 代码生成时间: 2025-10-18 18:17:07
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import com.example.demo.exception.PaymentException;
import com.example.demo.service.PaymentService;

@RestController
public class PaymentGatewayController {

    private final PaymentService paymentService;

    public PaymentGatewayController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/processPayment")
    public ResponseEntity<String> processPayment(@RequestParam String amount) {
        try {
            String paymentResult = paymentService.processPayment(amount);
            return ResponseEntity.ok(paymentResult);
        } catch (PaymentException e) {
            // Log the exception here
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<String> handlePaymentException(PaymentException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}

// PaymentService.java
package com.example.demo.service;

public interface PaymentService {
    String processPayment(String amount) throws PaymentException;
}

// PaymentServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.service.PaymentService;
import com.example.demo.exception.PaymentException;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public String processPayment(String amount) throws PaymentException {
        // Payment processing logic here
        // For demonstration, let's assume it always throws an exception
        throw new PaymentException("Payment processing failed");
    }
}

// PaymentException.java
package com.example.demo.exception;

public class PaymentException extends Exception {

    public PaymentException(String message) {
        super(message);
    }
}
