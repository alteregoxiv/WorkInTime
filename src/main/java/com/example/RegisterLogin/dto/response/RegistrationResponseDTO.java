package com.example.RegisterLogin.dto.response;

import com.example.RegisterLogin.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponseDTO {
    @NotBlank(message = "ID is required")
    public int id;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    public String email;

    @NotBlank(message = "CreatedAt is required")
    public Timestamp createdAt;

    @NotBlank(message = "UpdatedAt is required")
    public Timestamp updatedAt;

    @NotBlank(message = "Role is required")
    public String role;

    public RegistrationResponseDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.role = user.getRole().getRoleName();
    }
}
