package com.thuan.identiy_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationRequest {

    @Size(min = 8, message = "{username.min}")
    String username;

    @Size(min = 8, message = "{password.min}")
    private String password;
    String firstName;
    String lastName;
    LocalDate dob;
}
