package com.example.springlearnings.services.impl;

import com.example.springlearnings.services.interfaces.ISentimentAnalysisService;
import com.example.springlearnings.services.models.EmotionScore;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SentimentAnalysisService implements ISentimentAnalysisService {
    @Override
    public List<EmotionScore> fetchSentiment(String text) {
        return Collections.emptyList();
    }
}
