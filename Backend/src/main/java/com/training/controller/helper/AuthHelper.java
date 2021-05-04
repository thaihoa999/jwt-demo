package com.training.controller.helper;

import com.training.dto.request.LoginRequest;
import com.training.dto.request.SignUpRequest;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Auth helper.
 */
public class AuthHelper {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.]+@(.+)$";

    /**
     * Is signup empty field boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public static boolean isSignupEmptyField(SignUpRequest request) {
        return StringUtils.isBlank(request.getUserName())
                || StringUtils.isBlank(request.getPassword())
                || StringUtils.isBlank(request.getConfirmPassword())
                || StringUtils.isBlank(request.getEmail());
    }

    /**
     * Is login empty field boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public static boolean isLoginEmptyField(LoginRequest request) {
        return StringUtils.isBlank(request.getUserName())
                || StringUtils.isBlank(request.getPassword());
    }

    /**
     * Check length password boolean.
     * The password must be at least 3 characters and must not exceed 20 characters.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean checkLengthPassword(String password) {
        int length = password.length();

        return length < 3 || length > 20;
    }

    /**
     * Is valid email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * Is same password boolean.
     *
     * @param password        the password
     * @param confirmPassword the confirm password
     * @return the boolean
     */
    public static boolean isSamePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
