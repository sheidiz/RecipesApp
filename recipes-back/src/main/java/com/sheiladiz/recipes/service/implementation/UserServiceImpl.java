package com.sheiladiz.recipes.service.implementation;

import com.sheiladiz.recipes.entity.User;
import com.sheiladiz.recipes.exception.ResourceNotFoundException;
import com.sheiladiz.recipes.repository.UserRepository;
import com.sheiladiz.recipes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con id [" + id + "] no encontrado"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con email [" + email + "] no encontrado"));
    }
}
