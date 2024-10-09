package com.sheiladiz.recipes.mapper;

import com.sheiladiz.recipes.dto.user.RequestRegisterDto;
import com.sheiladiz.recipes.dto.user.ResponseUserDto;
import com.sheiladiz.recipes.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recipes", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User registerUserToUser(RequestRegisterDto requestRegisterDto);

    ResponseUserDto userToUserDto(User user);

}
