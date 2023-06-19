package com.example.RegisterLogin.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDTO {
    @NotBlank(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Role must be provided")
    @NotEmpty(message = "Role must be provided")
    @NotNull(message = "Role must be provided")
    private Long roleId;
}
