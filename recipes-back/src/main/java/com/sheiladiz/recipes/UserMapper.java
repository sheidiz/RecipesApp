package com.sheiladiz.recipes;

import com.sheiladiz.recipes.dto.user.RequestRegisterDto;
import com.sheiladiz.recipes.dto.user.ResponseUserDto;
import com.sheiladiz.recipes.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User registerUserToUser(RequestRegisterDto requestRegisterDto);

    ResponseUserDto userToUserDto(User user);

}
