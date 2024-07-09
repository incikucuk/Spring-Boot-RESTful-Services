package com.javaguide.spring_boot_restful_webservices.mapper;

import com.javaguide.spring_boot_restful_webservices.dto.UserDto;
import com.javaguide.spring_boot_restful_webservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper   //mapstructure library
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    //User  ve UserDto alanlarında farklı ifade edilen aynı alanlar maplemek için bu şkeilde gösterim yapılır.
   // @Mapping(source = "email",target = "emailAddress")
    User mapToUser(UserDto userDto);

    UserDto mapToDto(User user);
}
