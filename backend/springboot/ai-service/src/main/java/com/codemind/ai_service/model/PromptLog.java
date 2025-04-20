package com.codemind.ai_service.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "prompt_logs")
@Getter
@Setter
public class PromptLog {
    @Id
    private String id;

    @NotNull
    @Indexed
    private String prompt;

    private String response;
    private String llmUsed;

    @Indexed(name = "timestamp_index")
    private LocalDateTime timeStamp;
    private Map<String, Object> analysis;
}
