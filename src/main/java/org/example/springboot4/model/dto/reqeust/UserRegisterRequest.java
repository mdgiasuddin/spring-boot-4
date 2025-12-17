package org.example.springboot4.model.dto.reqeust;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.springboot4.model.enumeration.Role;

@Getter
@Setter
public class UserRegisterRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotNull
    private Role role;

    @AssertTrue(message = "Passwords do not match")
    public boolean isPasswordsMatch() {
        if (password == null || confirmPassword == null) {
            return true;
        }

        return password.equals(confirmPassword);
    }
}
