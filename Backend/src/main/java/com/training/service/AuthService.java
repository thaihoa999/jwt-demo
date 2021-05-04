package com.training.service;

import com.training.domain.User;

import java.util.List;

/**
 * The interface Auth service.
 */
public interface AuthService {
    /**
     * Gets all user.
     *
     * @return the all user
     */
    List<User> getAllUser();

    /**
     * Save or update user.
     *
     * @param user the user
     */
    void save(User user);

    /**
     * Exists user by user name boolean.
     *
     * @param userName the user name
     * @return the boolean
     */
    boolean existsUserByUserName(String userName);


    /**
     * Find user by email user.
     *
     * @param email the email
     * @return the user
     */
    User findUserByEmail(String email);

    User findUserByPasswordResetToken(String token);
}
