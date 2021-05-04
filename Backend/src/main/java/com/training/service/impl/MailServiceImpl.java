package com.training.service.impl;

import com.training.domain.User;
import com.training.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = Logger.getLogger(MailServiceImpl.class);

    private JavaMailSender mailSender;

    @Value("${default.user.password}")
    private String defaultPassword;

    @Override
    public void sendMail(User user, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Reset password");
        mailMessage.setText(body);
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("tonvd11892@gmail.com");

        try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            LOGGER.error("Cannot send mail to " + user.getEmail() + " with error ", e);
        }
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
