// 代码生成时间: 2025-10-10 21:32:41
package com.projectmanagementtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
public class ProjectManagementTool {

    private Map<String, String> projects = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementTool.class, args);
    }

    // 获取所有项目
    @GetMapping("/projects")
    public Map<String, String> getAllProjects() {
        return projects;
    }

    // 获取单个项目
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        String project = projects.get(projectId);
        if (project == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
        }
        return ResponseEntity.ok(project);
    }

    // 添加新项目
    @GetMapping("/projects/add/{projectId}/{projectName}")
    public ResponseEntity<?> addProject(@PathVariable String projectId, @PathVariable String projectName) {
        if (projects.containsKey(projectId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Project already exists");
        }
        projects.put(projectId, projectName);
        return ResponseEntity.ok("Project added successfully");
    }

    // 异常处理
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
