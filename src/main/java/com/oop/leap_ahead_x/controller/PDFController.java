package com.oop.leap_ahead_x.controller;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin
@RestController
@RequestMapping("/api/email")
public class PDFController {
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/sendpdf")
    public ResponseEntity<?> sendPdf(@RequestParam("pdf") MultipartFile pdfFile, @RequestParam("email") String email,@RequestParam("message") String emailMessage) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("ray.quek@gmail.com"); // replace with your own email address
            helper.setTo(email);
            helper.setSubject("PDF document");
            helper.setText(emailMessage);
            helper.addAttachment(pdfFile.getOriginalFilename(), pdfFile);
            mailSender.send(message);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + e.getMessage());
        }
    }

}

