// 代码生成时间: 2025-10-12 01:55:21
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Map;

@SpringBootApplication
@RestController
public class MedicationInteractionService {

    @GetMapping("/checkInteraction")
    public ResponseEntity<Map<String, String>> checkMedicationInteraction(@RequestParam String drug1, @RequestParam String drug2) {
        try {
            // 模拟的药物相互作用检查逻辑
            boolean interactionExists = checkForInteraction(drug1, drug2);
            if (interactionExists) {
                return ResponseEntity.ok(Map.of("message", "There is an interaction between the medications."));
            } else {
                return ResponseEntity.ok(Map.of("message", "No interaction detected."));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // 模拟的药物相互作用检查方法
    private boolean checkForInteraction(String drug1, String drug2) {
        // 此处应实现实际的药物相互作用检查逻辑
        // 以下为示例代码，实际情况应调用数据库或外部服务
        return "Aspirin".equals(drug1) && "Ibuprofen".equals(drug2);
    }

    // 异常处理
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }

    public static void main(String[] args) {
        SpringApplication.run(MedicationInteractionService.class, args);
    }
}