package com.codemind.ai_service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnalysisResponse {
    private String diff;
    private double similarity;
    private Regression regression;
}
