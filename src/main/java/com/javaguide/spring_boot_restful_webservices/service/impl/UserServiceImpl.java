package com.javaguide.spring_boot_restful_webservices.service.impl;

import com.javaguide.spring_boot_restful_webservices.dto.UserDto;
import com.javaguide.spring_boot_restful_webservices.entity.User;
import com.javaguide.spring_boot_restful_webservices.mapper.UserMapper;
import com.javaguide.spring_boot_restful_webservices.repository.UserRepository;
import com.javaguide.spring_boot_restful_webservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {   //db'e kaydeder
        //Convert UserDto into User JPA Entity
       User user = UserMapper.maptoUser(userDto);

       User savedUser = userRepository.save(user);

        //Convert User JPA entity to UserDto
       UserDto saveduserDto = UserMapper.mapToUserDto(savedUser);

       return saveduserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        User updatedUser = userRepository.save(existingUser);

        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
