// 代码生成时间: 2025-09-23 19:15:45
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class BatchFileRenamerService {

    public static void main(String[] args) {
        SpringApplication.run(BatchFileRenamerService.class, args);
    }

    @PostMapping("/rename")
    public String renameFiles(@RequestBody List<RenameRequest> requests) {
        try {
            for (RenameRequest request : requests) {
                Path sourcePath = Paths.get(request.getSource());
                Path targetPath = Paths.get(request.getTarget());
                File sourceFile = sourcePath.toFile();
                File targetFile = targetPath.toFile();
                if (sourceFile.renameTo(targetFile)) {
                    System.out.println("Renamed: " + request.getSource() + " to " + request.getTarget());
                } else {
                    System.out.println("Failed to rename: " + request.getSource() + " to " + request.getTarget());
                }
            }
            return "Files renamed successfully.";
        } catch (IOException e) {
            return "Error occurred: " + e.getMessage();
        }
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Service is up and running.";
    }

    static class RenameRequest {
        private String source;
        private String target;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }
    }
}
