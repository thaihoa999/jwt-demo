package com.training.dto.request;

import lombok.Data;

@Data
public class SignUpRequest extends LoginRequest {
    private String email;
    private String confirmPassword;
}
