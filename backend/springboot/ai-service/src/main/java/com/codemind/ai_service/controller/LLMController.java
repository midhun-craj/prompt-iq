package com.codemind.ai_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codemind.ai_service.model.PromptRequest;
import com.codemind.ai_service.model.PromptResponse;
import com.codemind.ai_service.service.LLMService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/llm")
@AllArgsConstructor
public class LLMController {
    
    private final LLMService llmService;

    @PostMapping("/prompt")
    public ResponseEntity<PromptResponse> handlePrompt(@RequestBody PromptRequest promptRequest) {
        PromptResponse llmReply = llmService.getLLMResponse(promptRequest.getPrompt(), promptRequest.getApikey());
        
        return ResponseEntity.ok(llmReply);
    }
}
