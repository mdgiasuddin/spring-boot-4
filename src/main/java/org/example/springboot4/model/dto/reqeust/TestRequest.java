package org.example.springboot4.model.dto.reqeust;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.springboot4.model.enumeration.AccountType;

import static org.example.springboot4.model.enumeration.AccountType.INDIVIDUAL;
import static org.example.springboot4.model.enumeration.AccountType.SHARED;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class TestRequest {
    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private AccountType type;

    @NotNull
    private Double amount;

    @AssertTrue(message = "For INDIVIDUAL account amount must be <= 100")
    public boolean isValidIndividualAmount() {
        if (type == null || amount == null) {
            return true;
        }

        return type != INDIVIDUAL || amount <= 100;
    }

    @AssertTrue(message = "For SHARED account amount must be >= 1000")
    public boolean isValidSharedAmount() {
        if (type == null || amount == null) {
            return true;
        }

        return type != SHARED || amount >= 1000;
    }
}
