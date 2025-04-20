package com.codemind.ai_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codemind.ai_service.model.PromptLog;
@Repository
public interface PromptLogRepository extends MongoRepository<PromptLog, String> {
    List<PromptLog> findTop5ByOrderByTimeStampDesc();    
}
