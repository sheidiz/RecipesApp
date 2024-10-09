package com.sheiladiz.recipes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidJwtException extends RuntimeException {
    private String message;
}
