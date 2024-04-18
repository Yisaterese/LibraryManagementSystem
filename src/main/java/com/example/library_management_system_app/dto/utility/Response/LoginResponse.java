package com.example.library_management_system_app.dto.utility.Response;

import lombok.Data;

@Data
public class LoginResponse {
    private String id;
    private String username;
    private boolean successful;
}
