package com.sheiladiz.recipes.service.implementation;

import com.sheiladiz.recipes.UserMapper;
import com.sheiladiz.recipes.dto.user.RequestLoginDto;
import com.sheiladiz.recipes.dto.user.RequestRegisterDto;
import com.sheiladiz.recipes.dto.user.ResponseLoginDto;
import com.sheiladiz.recipes.dto.user.ResponseUserDto;
import com.sheiladiz.recipes.entity.User;
import com.sheiladiz.recipes.exception.InvalidUserCredentialsException;
import com.sheiladiz.recipes.exception.ResourceAlreadyExistsException;
import com.sheiladiz.recipes.exception.ResourceNotFoundException;
import com.sheiladiz.recipes.repository.UserRepository;
import com.sheiladiz.recipes.security.JwtService;
import com.sheiladiz.recipes.service.AuthenticationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ResponseUserDto signUp(@Valid RequestRegisterDto requestRegisterDto) {
        if (userRepository.findByEmail(requestRegisterDto.email()).isPresent()) {
            throw new ResourceAlreadyExistsException("Ya hay una cuenta asociada con el email " + requestRegisterDto.email() + ".");
        }
        User user = userMapper.registerUserToUser(requestRegisterDto);
        user.setPassword(passwordEncoder.encode(requestRegisterDto.password()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        User savedUser = userRepository.save(user);

        return userMapper.userToUserDto(savedUser);
    }

    @Override
    @Transactional
    public ResponseLoginDto login(RequestLoginDto requestLoginDto) {
        User user = userRepository.findByEmail(requestLoginDto.email()).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestLoginDto.email(),
                            requestLoginDto.password()
                    )
            );
        } catch (BadCredentialsException ex) {
            throw new InvalidUserCredentialsException("Email y/o contraseña inválidos.");
        }

        String jwtToken = jwtService.generateToken(user);

        return new ResponseLoginDto(user.getId(), jwtToken, jwtService.getExpirationTime());

    }
}
