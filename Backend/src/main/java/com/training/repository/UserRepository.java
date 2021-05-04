package com.training.repository;

import com.training.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find by user name optional.
     *
     * @param userName the user name
     * @return the optional
     */
    Optional<User> findByUserName(String userName);

    /**
     * Exists user by user name boolean.
     *
     * @param userName the user name
     * @return the boolean
     */
    boolean existsUserByUserName(String userName);

    /**
     * Exists user by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    User findByEmail(String email);

    /**
     * Find user by reset pw token optional.
     *
     * @param token the token
     * @return the optional
     */
    User findUserByResetPwToken(String token);
}
