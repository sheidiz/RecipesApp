package com.sheiladiz.recipes.dto.user;

public record ResponseLoginDto(
        Long id,
        String token,
        Long expiresIn
) {
}
