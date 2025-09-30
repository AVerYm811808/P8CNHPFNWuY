// 代码生成时间: 2025-10-01 03:44:16
package com.example.keymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/keys")
public class KeyManagementService {

    private static final String[] KEYS = new String[] {"key1", "key2", "key3"};

    public static void main(String[] args) {
        SpringApplication.run(KeyManagementService.class, args);
    }

    // 获取所有密钥
    @GetMapping
    public String[] getAllKeys() {
        return KEYS;
    }

    // 根据ID获取密钥
    @GetMapping("/{id}")
    public String getKeyById(@PathVariable String id) {
        for (String key : KEYS) {
            if (key.equals(id)) {
                return key;
            }
        }
        throw new KeyNotFoundException("Key not found with ID: " + id);
    }

    // 异常处理
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(KeyNotFoundException.class)
    public String keyNotFound(KeyNotFoundException ex) {
        return ex.getMessage();
    }

    // 自定义异常类
    static class KeyNotFoundException extends RuntimeException {
        public KeyNotFoundException(String message) {
            super(message);
        }
    }
}