// 代码生成时间: 2025-09-23 01:26:46
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
public class NetworkStatusChecker {

    private final RestTemplate restTemplate;

    public NetworkStatusChecker(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkConnection(@RequestParam String url) {
        try {
            InetAddress.getByName(url);
            return ResponseEntity.ok("Connection successful to: " + url);
        } catch (UnknownHostException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Connection failed: Host is unknown");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(NetworkStatusChecker.class, args);
    }
}

// Exception handling can be further improved by creating a custom exception class and a global exception handler
// that handles different types of exceptions. This example only includes basic error handling within the method.