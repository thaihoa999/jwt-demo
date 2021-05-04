package com.training.controller.helper;

import com.training.domain.User;
import com.training.dto.response.ListUserResponse;
import com.training.dto.response.MessageResponse;
import com.training.service.AuthService;
import com.training.util.RestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/user")
public class UserController {

    private AuthService authService;

    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser() {
        List<User> users = authService.getAllUser();
        if (CollectionUtils.isEmpty(users)) {
            return RestUtils.buildBadRequest(new MessageResponse("There are no users !"));
        }

        List<ListUserResponse> response = users.stream()
                        .map(data -> new ListUserResponse(data.getUserName(), data.getEmail()))
                        .collect(Collectors.toList());

        return RestUtils.buildOk(response);
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
