package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.EmailService;
import com.hilmibaskoparan.business.requests.authRequests.SendEmailAdminRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/email/sendAdmin")
    public void sendEmailForAdmin(@RequestBody SendEmailAdminRequest sendEmailAdminRequest) {
        emailService.sendEmailForAdmin(sendEmailAdminRequest);
    }
}
