package com.guide.java.api.mapper;


import com.guide.java.api.dto.UserDTO;
import com.guide.java.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    /*
    *  if you dont want to display the field remove it from DTO
    *  if you use ignore = null @Mapping(source = "password", target = "token" ,ignore = true)
    */

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(source = "username", target = "token")
    UserDTO userToUserDto(User user);
}
