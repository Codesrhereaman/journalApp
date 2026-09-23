package net.codesrhereaman.jounalapp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.codesrhereaman.jounalapp.model.SentimentData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class EmailConsumerService {

    private final EmailSenderService emailSenderService;

//    @KafkaListener(topics = "weekly-assignment", groupId = "weekly-assignment-group")
//    public void consume(SentimentData sentimentData){
//       sendEmail(sentimentData);
//    }

    private void sendEmail(SentimentData sentimentData) {
        emailSenderService.sendEmail(sentimentData.getEmail(), "Sentiment for previous week", sentimentData.getSentiment());
    }


}
