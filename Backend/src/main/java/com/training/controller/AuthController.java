package com.training.controller;

import com.training.config.security.jwt.JwtUtils;
import com.training.controller.helper.AuthHelper;
import com.training.domain.User;
import com.training.dto.request.LoginRequest;
import com.training.dto.request.ResetPasswordRequest;
import com.training.dto.request.SignUpRequest;
import com.training.dto.response.JwtResponse;
import com.training.dto.response.MessageResponse;
import com.training.service.AuthService;
import com.training.service.MailService;
import com.training.util.RestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin()
@RequestMapping(value = "/api/auth")
public class AuthController {

    private AuthService authService;

    private MailService mailService;

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        if (AuthHelper.isSignupEmptyField(request)) {
            return RestUtils.buildBadRequest(new MessageResponse("Username, password or email is empty !"));
        } else if (AuthHelper.isSamePassword(request.getPassword(), request.getConfirmPassword())) {
            return RestUtils.buildBadRequest(new MessageResponse("New passwords do not match !"));
        } else if (AuthHelper.checkLengthPassword(request.getPassword())) {
            return RestUtils.buildBadRequest(
                    new MessageResponse("Password must be at least 3 characters and must not exceed 20 characters !"));
        } else if (!AuthHelper.isValidEmail(request.getEmail())) {
            return RestUtils.buildBadRequest(new MessageResponse("Email is wrong format !"));
        } else if (authService.existsUserByUserName(request.getUserName())) {
            return RestUtils.buildBadRequest(new MessageResponse("User name already exists !"));
        }

        authService.save(new User(request.getUserName(), passwordEncoder.encode(request.getPassword()), request.getEmail()));

        return RestUtils.buildOk(new MessageResponse("Register successfully."));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (AuthHelper.isLoginEmptyField(request)) {
            return RestUtils.buildBadRequest(new MessageResponse("Username or password is empty !"));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        JwtResponse response = JwtResponse.builder()
                .token(jwt)
                .message("Login successfully.")
                .build();

        return RestUtils.buildOk(response);
    }

    @PostMapping("/confirmResetPassword")
    public ResponseEntity<?> confirmResetPassword(HttpServletRequest servletRequest,
                                           @RequestBody SignUpRequest request) {
        String email = request.getEmail();
        if (StringUtils.isBlank(email)) {
            return RestUtils.buildBadRequest(new MessageResponse("Email is empty !"));
        } else if (!AuthHelper.isValidEmail(email)) {
            return RestUtils.buildBadRequest(new MessageResponse("Email is wrong format !"));
        }

        User user = authService.findUserByEmail(email);

        if (null == user) {
            return RestUtils.buildBadRequest(new MessageResponse("Email does not exist !"));
        }

        String token = UUID.randomUUID().toString();
        user.setResetPwToken(token);
        authService.save(user);

        String resetUrl = "http://localhost:4200/update-password".concat("/?token=").concat(token);
        String body = "To complete the password reset process, \nplease click here: \n" + resetUrl;

        mailService.sendMail(user, body);

        return RestUtils.buildOk(new MessageResponse("Please check your email to confirm reset password."));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ResetPasswordRequest request) {
        User user = authService.findUserByPasswordResetToken(request.getToken());
        if (null == user) {
            return RestUtils.buildBadRequest(new MessageResponse("Invalid user !"));
        } else if (!AuthHelper.isSamePassword(request.getNewPassword(), request.getConfirmPassword())) {
            return RestUtils.buildBadRequest(new MessageResponse("The confirm new password does not match !"));
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        authService.save(user);

        return RestUtils.buildOk(new MessageResponse("Reset password successfully."));
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
