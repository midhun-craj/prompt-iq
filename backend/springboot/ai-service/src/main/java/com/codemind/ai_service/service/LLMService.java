package com.codemind.ai_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.codemind.ai_service.model.AnalysisRequest;
import com.codemind.ai_service.model.AnalysisResponse;
import com.codemind.ai_service.model.PromptLog;
import com.codemind.ai_service.model.PromptResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LLMService {

    private final WebClient webClient;
    private final AnalysisClient analysisClient;
    private final PromptLogService promptLogService;

    @Value("${openai.api.key}")
    private String apiKey;
 
    public LLMService(WebClient.Builder builder, AnalysisClient analysisClient, PromptLogService promptLogService) {
        this.webClient = builder.baseUrl("https://api.openai.com/v1").build();
        this.analysisClient = analysisClient;
        this.promptLogService = promptLogService;
    }

    public PromptResponse getLLMResponse(String prompt, String userAPIKey) {
        String keyToUse = (userAPIKey != null && !userAPIKey.isEmpty() ? userAPIKey : apiKey);

        String payLoad = buildPayload(prompt);

        String llmResponse = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + keyToUse)
                    .header("Content-Type", "application/json")
                    .bodyValue(payLoad)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

        List<PromptLog> recentLogs = promptLogService.getRecentPromptLogs();

        String oldPrompt = "NA";
        String oldResponse = "NA";
        
        if (!recentLogs.isEmpty()) {
            PromptLog lastLog = recentLogs.get(0);
            oldPrompt = lastLog.getPrompt();
            oldResponse = lastLog.getResponse();
        }

        List<String> history = recentLogs.stream()
                .map(PromptLog::getPrompt)
                .toList();
        
        AnalysisRequest analysisRequest = new AnalysisRequest();
        analysisRequest.setOld_prompt(oldPrompt);
        analysisRequest.setNew_prompt(prompt);
        analysisRequest.setOld_response(oldResponse);
        analysisRequest.setNew_response(llmResponse);
        analysisRequest.setHistory(history);

        AnalysisResponse analysisResponse = analysisClient.sendForAnalysis(analysisRequest);

        return new PromptResponse(llmResponse, analysisResponse);
    }

    private String buildPayload(String prompt) {
        Map<String, Object> request = new HashMap<>();
        request.put("model", "gpt-3.5-turbo");

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        request.put("messages", List.of(message));

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(request);
        } catch(Exception e) {
            throw new RuntimeException("Failed to build LLM payload", e);
        }
    }
}
