package com.training.dto.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {

    private String token;
    private String newPassword;
    private String confirmPassword;
}
