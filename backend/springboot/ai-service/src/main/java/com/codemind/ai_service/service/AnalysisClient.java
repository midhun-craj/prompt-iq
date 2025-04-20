package com.codemind.ai_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.codemind.ai_service.model.AnalysisRequest;
import com.codemind.ai_service.model.AnalysisResponse;

import org.springframework.beans.factory.annotation.Value;

@Service
public class AnalysisClient {
    private final WebClient webClient;

    @Value("${analysis-service.url}")
    private String analysisServiceUrl;

    public AnalysisClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(analysisServiceUrl).build();
    }

    public AnalysisResponse sendForAnalysis(AnalysisRequest analysisRequest) {

        return webClient.post()
                .uri("/analyze/")
                .header("Content-Type", "application/json")
                .bodyValue(analysisRequest)
                .retrieve()
                .bodyToMono(AnalysisResponse.class)
                .block();
    }
}
