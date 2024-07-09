package com.javaguide.spring_boot_restful_webservices.service.impl;

import com.javaguide.spring_boot_restful_webservices.dto.UserDto;
import com.javaguide.spring_boot_restful_webservices.entity.User;
import com.javaguide.spring_boot_restful_webservices.exception.EmailAlreadyExistsException;
import com.javaguide.spring_boot_restful_webservices.exception.ResourceNotFoundException;
import com.javaguide.spring_boot_restful_webservices.mapper.AutoUserMapper;
import com.javaguide.spring_boot_restful_webservices.repository.UserRepository;
import com.javaguide.spring_boot_restful_webservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {   //db'e kaydeder
        //Convert UserDto into User JPA Entity
       //User user = UserMapper.maptoUser(userDto);
       // User user = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail()); //db'de boyle bir kullanıcı var mı kontrolü yapılacak
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists For User");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);  //used Map structure

        User savedUser = userRepository.save(user);

        //Convert User JPA entity to UserDto
       //UserDto saveduserDto = UserMapper.mapToUserDto(savedUser);

        //UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToDto(savedUser);

       return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId)
        );
        //return UserMapper.mapToUserDto(user);
        //return  modelMapper.map(user,UserDto.class);
        return AutoUserMapper.MAPPER.mapToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
       // return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
       // return users.stream().map((user) ->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return users.stream().map(AutoUserMapper.MAPPER::mapToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User","id",user.getId())
        );

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        User updatedUser = userRepository.save(existingUser);

        //return UserMapper.mapToUserDto(updatedUser);
        //return modelMapper.map(updatedUser,UserDto.class);
        return AutoUserMapper.MAPPER.mapToDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId)
        );
        userRepository.deleteById(userId);
    }
}
/*
Modelmapper'ın kullanımı daha kolay ve kurulumu daha hızlıdır,
ancak MapStruct daha iyi performans ve derleme zamanı güvenliği
 sunarak onu karmaşık ve performansa duyarlı uygulamalar için daha uygun hale getirir.
İkisi arasındaki seçim, özel proje gereksinimlerinize ve önceliklerinize bağlıdır. En iyi uygulamalar için,
performans ve tip güvenliğinin çok önemli olduğu daha büyük, üretim düzeyinde projeler için mapstruct'ı tercih
edin ve kullanım kolaylığının öncelikli olduğu daha basit veya prototip uygulamalar için modelmapper'ı kullanın.
 */
