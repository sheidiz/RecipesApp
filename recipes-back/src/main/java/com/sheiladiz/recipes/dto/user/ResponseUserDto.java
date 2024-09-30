package com.sheiladiz.recipes.dto.user;

public record ResponseUserDto(
        Long id,
        String name,
        String surname,
        String email
) {
}
