package com.codemind.ai_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codemind.ai_service.model.PromptLog;
import com.codemind.ai_service.service.PromptLogService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/prompts")
@AllArgsConstructor
public class PromptLogController {

    private PromptLogService promptLogService;

    @PostMapping
    public PromptLog createPromptLog(@RequestBody PromptLog promptLog) {
        return promptLogService.savePromptLog(promptLog);
    }

    @GetMapping
    public List<PromptLog> getAllPromptLogs() {
        return promptLogService.getAllPromptLogs();
    } 
    
    @GetMapping("/{id}")
    public PromptLog gePromptLog(@PathVariable String id) {
        return promptLogService.getPromptLogById(id)
            .orElseThrow(() -> new RuntimeException("Prompt log not found."));
    }
}
