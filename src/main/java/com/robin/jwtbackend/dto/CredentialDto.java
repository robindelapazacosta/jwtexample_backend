package com.robin.jwtbackend.dto;


//Se usa cuando es para recibir informacion solamente
public record CredentialDto(String login, char[] password) {}
