package com.sheiladiz.recipes.exception;

public record ErrorResponse(
        int statusCode,
        String message
) {

}