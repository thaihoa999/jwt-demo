package com.training.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListUserResponse {
    private String userName;
    private String email;
}
