package com.psw.generator.controller;

import com.psw.generator.exception.PasswordGeneratorException;
import com.psw.generator.model.PasswordProperties;
import com.psw.generator.service.PasswordBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {

    @Autowired
    private PasswordBuilderService passwordBuilderService;

    @GetMapping("/psw")
    public ResponseEntity<String> passwordGenerator(
            @RequestParam int length,
            @RequestParam boolean isLowerCase,
            @RequestParam boolean isUpperCase,
            @RequestParam boolean isNumbers,
            @RequestParam boolean isSymbols){
        PasswordProperties passwordProperties = new PasswordProperties(length, isLowerCase, isUpperCase, isNumbers, isSymbols);
        try {
            return new ResponseEntity<>(passwordBuilderService.passwordBuilder(passwordProperties), HttpStatus.CREATED);
        } catch (PasswordGeneratorException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
