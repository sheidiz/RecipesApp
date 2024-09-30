package com.sheiladiz.recipes.dto.user;

import jakarta.validation.constraints.*;

public record RequestLoginDto(
        @NotBlank
        String password,
        @NotNull
        String email
) {
}
