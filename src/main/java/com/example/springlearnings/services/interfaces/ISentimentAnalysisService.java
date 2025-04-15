package com.example.springlearnings.services.interfaces;

import com.example.springlearnings.services.models.EmotionScore;

import java.util.List;

public interface ISentimentAnalysisService {
    List<EmotionScore> fetchSentiment(String text);
}
