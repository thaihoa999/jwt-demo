package com.training.service;

import com.training.domain.User;

public interface MailService {

    void sendMail(User user, String body);
}
