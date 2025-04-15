package com.example.springlearnings.services.schedulers;

import com.example.springlearnings.services.interfaces.IPublishSentimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeeklySentimentScheduler {

    @Autowired
    private IPublishSentimentService publishSentimentService;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void publishSentiments() {
        publishSentimentService.publishWeeklyJournalSentiments();
    }
}
