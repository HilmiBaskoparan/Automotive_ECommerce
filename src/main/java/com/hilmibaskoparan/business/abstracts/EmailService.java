package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.authRequests.SendEmailAdminRequest;
import com.hilmibaskoparan.model.entity.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {

    public void sendEmail(User user) throws UnsupportedEncodingException, MessagingException;

    public void sendEmailForAdmin(SendEmailAdminRequest sendEmailAdminRequest);

    public String confirmEmail(String confirmationToken);

    public void sendEmailForPayment(int shoppingCardId);
}
