package com.codemind.ai_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codemind.ai_service.model.PromptLog;
import com.codemind.ai_service.repository.PromptLogRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PromptLogService {

    private PromptLogRepository promptLogRepository;

    public PromptLog savePromptLog(PromptLog promptLog) {
        return promptLogRepository.save(promptLog);
    }

    public List<PromptLog> getAllPromptLogs() {
        return promptLogRepository.findAll();
    }

    public Optional<PromptLog> getPromptLogById(String id) {
        return promptLogRepository.findById(id);
    }

    public List<PromptLog> getRecentPromptLogs() {
        return promptLogRepository.findTop5ByOrderByTimeStampDesc();
    }
}
