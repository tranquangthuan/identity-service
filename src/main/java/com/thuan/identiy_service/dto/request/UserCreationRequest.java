package com.thuan.identiy_service.dto.request;

import com.thuan.identiy_service.validation.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 8, message = "{username.min}")
    String username;

    @Size(min = 8, message = "{password.min}")
    private String password;
    String firstName;
    String lastName;

    @DobConstraint(min = 10, message = "{dob.min}")
    LocalDate dob;
}
