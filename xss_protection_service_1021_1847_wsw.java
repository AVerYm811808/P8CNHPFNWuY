// 代码生成时间: 2025-10-21 18:47:16
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping("/api")
public class XssProtectionService extends ResponseEntityExceptionHandler {

    // REST API to handle GET requests with XSS protection
    @GetMapping("/xss")
    public ResponseEntity<String> getXssProtectedText(@RequestParam String input) {
        // Sanitize input to prevent XSS attacks
        String sanitizedInput = sanitizeInput(input);
        return ResponseEntity.ok("Received sanitized input: " + sanitizedInput);
    }

    // REST API to handle POST requests with XSS protection
    @PostMapping("/xss")
    public ResponseEntity<String> postXssProtectedText(@RequestBody String input) {
        // Sanitize input to prevent XSS attacks
        String sanitizedInput = sanitizeInput(input);
        return ResponseEntity.ok("Received sanitized input: " + sanitizedInput);
    }

    // Exception handler for any exceptions that may occur
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body("An error occurred: " + ex.getMessage());
    }

    // Helper method to sanitize input to prevent XSS attacks
    private String sanitizeInput(String input) {
        // Implement a real sanitization logic here, this is just an example
        // In real-world scenarios, consider using a library like OWASP Java HTML Sanitizer
        return input.replaceAll("<script>.*?</script>","").replaceAll("<.*?>","");
    }
}
