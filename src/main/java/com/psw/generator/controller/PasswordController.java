package com.psw.generator.controller;

import com.psw.generator.exception.PasswordGeneratorException;
import com.psw.generator.model.PasswordDto;
import com.psw.generator.model.PasswordProperties;
import com.psw.generator.service.PasswordBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PasswordController {

    @Autowired
    private PasswordBuilderService passwordBuilderService;

    @PostMapping("/psw")
    public ResponseEntity<String> passwordGenerator(
            @RequestBody PasswordDto dto) {
        PasswordProperties passwordProperties = new PasswordProperties(dto);
        try {
            System.out.println("teste")
            return new ResponseEntity<>(passwordBuilderService.passwordBuilder(passwordProperties), HttpStatus.CREATED);
        } catch (PasswordGeneratorException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
