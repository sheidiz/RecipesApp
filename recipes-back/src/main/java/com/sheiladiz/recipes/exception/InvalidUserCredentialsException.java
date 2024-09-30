package com.sheiladiz.recipes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidUserCredentialsException extends RuntimeException {
    private String message;
}