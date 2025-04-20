package com.codemind.ai_service.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisRequest {
    private String old_prompt;
    private String new_prompt;
    private String old_response;
    private String new_response;
    private List<String> history;
}
