package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.service.EmailSenderService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/emailrequest", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmailController {
    private final EmailSenderService senderService;

    public EmailController(EmailSenderService senderService) {
        this.senderService = senderService;
    }
    @PostMapping
    @ApiResponse(responseCode = "201")
    public void sendEmail(@RequestBody Map<String, String> details) {
        senderService.sendEmail(details.get("toEmail"),details.get("subject"),details.get("body") );
    }

}
