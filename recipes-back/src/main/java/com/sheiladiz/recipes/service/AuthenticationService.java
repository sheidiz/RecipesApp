package com.sheiladiz.recipes.service;

import com.sheiladiz.recipes.dto.user.RequestLoginDto;
import com.sheiladiz.recipes.dto.user.RequestRegisterDto;
import com.sheiladiz.recipes.dto.user.ResponseLoginDto;
import com.sheiladiz.recipes.dto.user.ResponseUserDto;
import jakarta.validation.Valid;

public interface AuthenticationService {
    ResponseUserDto signUp(@Valid RequestRegisterDto requestRegisterDto);

    ResponseLoginDto login(RequestLoginDto requestLoginDto);
}
