// 代码生成时间: 2025-10-02 03:37:18
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
@RequestMapping("/configs")
public class ConfigManagerService {

    private final Environment env;

    @Autowired
    public ConfigManagerService(Environment env) {
        this.env = env;
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> getConfigValue(@PathVariable String key) {
        try {
            String value = env.getProperty(key);
            if (value == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Config value not found for key: " + key);
            } else {
                return ResponseEntity.ok(value);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigManagerService.class, args);
    }
}
