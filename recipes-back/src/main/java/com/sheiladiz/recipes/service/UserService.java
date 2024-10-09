package com.sheiladiz.recipes.service;

import com.sheiladiz.recipes.entity.User;

public interface UserService {
    User getUserById(Long id);
    User getUserByEmail(String email);
}
