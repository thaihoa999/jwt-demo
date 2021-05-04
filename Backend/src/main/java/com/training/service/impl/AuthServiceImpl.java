package com.training.service.impl;

import com.training.domain.User;
import com.training.repository.UserRepository;
import com.training.service.AuthService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = Logger.getLogger(AuthServiceImpl.class);

    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        // Handle business logic if any
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        try {
            // Handle business logic if any
            userRepository.save(user);
        } catch (Exception e) {
            LOGGER.error("Cannot save user with error: ", e);
        }
    }

    @Override
    public boolean existsUserByUserName(String userName) {
        // Handle business logic if any
        return userRepository.existsUserByUserName(userName);
    }

    @Override
    public User findUserByEmail(String email) {
        // Handle business logic if any
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByPasswordResetToken(String token) {
        // Handle business logic if any
        return userRepository.findUserByResetPwToken(token);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
