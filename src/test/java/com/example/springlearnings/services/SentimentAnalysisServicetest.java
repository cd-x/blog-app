package com.example.springlearnings.services;

import com.example.springlearnings.services.models.EmotionScore;
import com.example.springlearnings.setup.TestHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class SentimentAnalysisServicetest {

    @Test
    public void test() {
        List<EmotionScore> emotionScores = TestHelper.createObjectListFromJsonFile("emotion_score.json", EmotionScore.class);
        log.info("Emotion scores: {}", emotionScores.size());
    }
}
