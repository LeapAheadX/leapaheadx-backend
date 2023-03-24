package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    private final UserService userService;


    public EmailSenderService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    public void sendEmail(String toEmail,
                          String subject,
                          String body){
        SimpleMailMessage message = new SimpleMailMessage();
        String email = userService.get(UUID.fromString(toEmail)).getEmail();
        message.setFrom("zikaiho@gmail.com");
        message.setTo(email);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);

        System.out.println("Mail sent");
    }
}
