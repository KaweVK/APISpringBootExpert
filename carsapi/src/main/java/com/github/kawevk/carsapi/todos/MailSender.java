package com.github.kawevk.carsapi.todos;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void sendMail(String to, String subject, String text) {
        // Simulate sending an email
        System.out.println("Sending email...");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Text: " + text);
        System.out.println("Email sent successfully!");
    }

}
