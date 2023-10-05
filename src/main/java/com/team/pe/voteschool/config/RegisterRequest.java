package com.team.pe.voteschool.config;

import com.team.pe.voteschool.models.UserCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private UserCategory category;
}
