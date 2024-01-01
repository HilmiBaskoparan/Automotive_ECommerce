package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.EmailService;
import com.hilmibaskoparan.business.abstracts.ShoppingCardService;
import com.hilmibaskoparan.business.requests.authRequests.SendEmailAdminRequest;
import com.hilmibaskoparan.model.entity.*;
import com.hilmibaskoparan.repository.ConfirmationTokenRepository;
import com.hilmibaskoparan.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;
    private final ShoppingCardService shoppingCardService;

    //@Value("app.baseUrl")
    private String baseUrl;

    public EmailServiceImpl(JavaMailSender javaMailSender, ConfirmationTokenRepository confirmationTokenRepository, UserRepository userRepository, ShoppingCardService shoppingCardService) {
        this.javaMailSender = javaMailSender;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userRepository = userRepository;
        this.shoppingCardService = shoppingCardService;
    }

    @Override
    public void sendEmail(User user) throws UnsupportedEncodingException, MessagingException {

        String token = UUID.randomUUID().toString();
        EmailConfirmationToken emailConfirmationToken = new EmailConfirmationToken(token, user);
        confirmationTokenRepository.save(emailConfirmationToken);

        String toAddress = user.getEmail();
        String fromAddress = "deneme@gmail.com";
        String senderName = "Hilmi Otomotiv";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>" + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>" + "Hilmi Ticaret.";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = baseUrl + "/api/v1/auth/confirm-account?token="
                + emailConfirmationToken.getConfirmationToken();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);

        javaMailSender.send(message);

    }

    @Override
    public void sendEmailForAdmin(SendEmailAdminRequest sendEmailAdminRequest) {

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("h.baskoparan@gmail.com");
            helper.setFrom(sendEmailAdminRequest.getName());
            helper.setSubject(sendEmailAdminRequest.getEmail());
            helper.setText(sendEmailAdminRequest.getContent(), true);
        } catch (Exception e) {
            // TODO: handle exception
        }
        javaMailSender.send(message);
    }

    @Override
    public String confirmEmail(String confirmationToken) {

        EmailConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken).get();

        if (token != null) {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail()).get();
            user.setEmailVerified(true);
            userRepository.save(user);
            return "Email verified successfully!";
        }

        return "Error: Couldn't verify email";
    }

    @Override
    public void sendEmailForPayment(int shoppingCardId) {
        Customer customer = shoppingCardService.findById(shoppingCardId).getCustomer();
        String body = generateBody(customer);
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(customer.getEmail());
            helper.setSubject("YOUR ORDER");
            helper.setText(body, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }

    private String generateBody(Customer customer) {

        ShoppingCard shoppingCard = customer.getShoppingCard();
        StringBuilder bodyBuilder = new StringBuilder();

        bodyBuilder.append("<p>Merhaba,</p>");
        bodyBuilder.append(
                "<p>Bu e-postayı aldığınız için teşekkür ederiz. Aşağıdaki ürünleri başarıyla satın aldığınızı bildirmek istiyoruz:</p>");

        bodyBuilder.append("<ul>");

        for (ShoppingCardItem item : shoppingCard.getShoppingCardItems()) {
            bodyBuilder.append("<li>Ürün Adı: ").append(item.getProduct().getName()).append("</li>");
            bodyBuilder.append("<li>Ürün Fiyatı: ").append(item.getProduct().getPrice()).append("</li>");
        }

        bodyBuilder.append("<p>Satın Alma Tarihi: ").append(new Date()).append("</p>");
        bodyBuilder.append(
                "<p>Siparişiniz en kısa sürede hazırlanacak ve kargoya verilecektir. Siparişinizin durumu hakkında daha fazla bilgi almak isterseniz, hesabınıza giriş yapabilir ve sipariş takibi bölümünden güncel durumu kontrol edebilirsiniz.</p>");
        bodyBuilder.append(
                "<p>Herhangi bir sorunuz veya endişeniz varsa, lütfen bize ulaşmaktan çekinmeyin. Yardımcı olmaktan mutluluk duyarız.</p>");
        bodyBuilder.append("<p>Teşekkürler,<br>[Hilmi OTOMOTİV]</p>");

        String body = bodyBuilder.toString();
        return body;
    }
}
