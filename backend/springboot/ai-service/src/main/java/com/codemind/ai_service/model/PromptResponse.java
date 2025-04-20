package com.codemind.ai_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PromptResponse {
    private String llmResponse;
    private AnalysisResponse analysis;
}
