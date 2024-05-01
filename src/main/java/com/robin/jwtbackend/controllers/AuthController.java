package com.robin.jwtbackend.controllers;

import com.robin.jwtbackend.config.UserAuthProvider;
import com.robin.jwtbackend.dto.CredentialDto;
import com.robin.jwtbackend.dto.UserDto;
import com.robin.jwtbackend.dto.UserRegisterDto;
import com.robin.jwtbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;

    private final UserAuthProvider userAuthProvider;


    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialDto credentialDto)
    {
        UserDto userDto= userService.login(credentialDto); //Lanza excepcion sino logra loguiarse
        userDto.setToken(userAuthProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterDto registerDto)
    {
        UserDto userDto= userService.register(registerDto);
        userDto.setToken(userAuthProvider.createToken(userDto));
        return ResponseEntity.created(URI.create("/users/"+userDto.getId())).body(userDto);

    }


}
