package net.codesrhereaman.jounalapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSenderService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String subject, String body){
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(to);
            email.setSubject(subject);
            email.setText(body);
            emailSender.send(email);
        } catch (Exception e) {
            log.error("unable send mail",to,e);
        }
    }
}
