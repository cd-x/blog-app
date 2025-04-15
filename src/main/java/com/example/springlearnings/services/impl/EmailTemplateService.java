package com.example.springlearnings.services.impl;

import com.example.springlearnings.services.models.EmailTemplate;
import com.example.springlearnings.services.models.EmotionScore;
import com.example.springlearnings.utils.EmotionChartGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class EmailTemplateService {

    @Autowired
    private TemplateEngine templateEngine;

    public EmailTemplate buildEmailTemplate(String username, String email, List<EmotionScore> emotionScores) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("metrics", emotionScores);
        data.put("chartUrlOrBase64", getChart(emotionScores));
        Context context = new Context();
        context.setVariables(data);
        String emailBody = templateEngine.process("sentiment-template", context);
        return new EmailTemplate(email, "Weekly Sentiment Analysis", emailBody);
    }

    private String getChart(List<EmotionScore> emotionScores) {
        try {
            return EmotionChartGenerator.generateEmotionChartBase64(emotionScores);
        } catch (Exception e) {
            log.error("Unable to generate chart!");
        }
        return null;
    }
}
