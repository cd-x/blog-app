package com.example.springlearnings.services.impl;

import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.services.interfaces.IEmailService;
import com.example.springlearnings.services.interfaces.IPublishSentimentService;
import com.example.springlearnings.services.interfaces.ISentimentAnalysisService;
import com.example.springlearnings.services.interfaces.IUserManagementService;
import com.example.springlearnings.services.models.EmailTemplate;
import com.example.springlearnings.services.models.EmotionScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PublishSentimentService implements IPublishSentimentService {
    @Autowired
    private IUserManagementService userManagementService;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private ISentimentAnalysisService sentimentAnalysisService;
    @Autowired
    private EmailTemplateService emailTemplateService;

    @Override
    public void publishWeeklyJournalSentiments() {
        Map<String, String> usernameEmailMap = userManagementService.getUsernameEmailMapWithSentimentOn();
        List<EmailTemplate> emailTemplateList = new ArrayList<>();
        usernameEmailMap.keySet().forEach(username -> {
            EmailTemplate emailTemplate = buildEmailTemplateByUsername(username, usernameEmailMap.get(username));
            emailTemplateList.add(emailTemplate);
        });
        emailTemplateList.forEach(emailTemplate -> {
            emailService.sendEmail(emailTemplate.getEmail(), emailTemplate.getSubject(), emailTemplate.getBody());
        });
    }

    private EmailTemplate buildEmailTemplateByUsername(String username, String email) {
        List<String> journals = userManagementService.getUserByUserName(username).getJournalList().stream().map(Journal::getContent).toList();
        List<EmotionScore> sentiment = sentimentAnalysisService.fetchSentiment(journals.toString());
        return emailTemplateService.buildEmailTemplate(username, email, sentiment);
    }
}
