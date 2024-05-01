package com.robin.jwtbackend.services;

import com.robin.jwtbackend.dto.CredentialDto;
import com.robin.jwtbackend.dto.UserDto;
import com.robin.jwtbackend.dto.UserRegisterDto;
import com.robin.jwtbackend.exception.AppException;
import com.robin.jwtbackend.mappers.UserMapper;
import com.robin.jwtbackend.model.User;
import com.robin.jwtbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;


    /*Hasta aqui no le he asignado el token */
    public UserDto login(CredentialDto credentialDto) {

        User user= userRepository.findByLogin(credentialDto.login()).orElseThrow(
                 ()->new AppException("User doesn't exist", HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialDto.password()) ,user.getPassword()))
        {
             return userMapper.toUserDto(user);
        }
        else
        {
            throw new AppException("Incorrect password", HttpStatus.BAD_REQUEST);
        }

    }

    public UserDto register(UserRegisterDto registerDto) {

        Optional<User> ouser= userRepository.findByLogin(registerDto.login());
        if(ouser.isPresent())
        {
            throw new AppException("Login already exist",HttpStatus.BAD_REQUEST);
        }

        User user= userMapper.userRegistrationToUser(registerDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(registerDto.password())));

        return  userMapper.toUserDto(userRepository.save(user)) ;
    }
}
