package com.psw.generator.service;

import com.psw.generator.exception.InvalidOperationException;
import com.psw.generator.model.PasswordProperties;
import com.psw.generator.util.Characters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import static com.psw.generator.util.PasswordGlobalProperties.MAX_PSW_LENGTH;
import static com.psw.generator.util.PasswordGlobalProperties.MIN_PSW_LENGTH;

@Service
@Slf4j
public class PasswordBuilderService {

    private final SecureRandom secureRandom = new SecureRandom();

    public String passwordBuilder(PasswordProperties passwordProperties){
        if (passwordProperties.getLength() < MIN_PSW_LENGTH) passwordProperties.setLength(MIN_PSW_LENGTH);
        if (passwordProperties.getLength() > MAX_PSW_LENGTH) passwordProperties.setLength(MAX_PSW_LENGTH);
        String allowedCharacters = Characters.getAllowedCharacters(passwordProperties);
        if (allowedCharacters.length() == 0) throw new InvalidOperationException("You must choose at least one kind of character.");
        char[] password =  new char[passwordProperties.getLength()];
        for (int position = 0; password.length > position; position++){
            password[position] = allowedCharacters.toCharArray()[secureRandom.nextInt(allowedCharacters.length())];
        }
        boolean containsAllMandatoryCharacters = false;
        while (!containsAllMandatoryCharacters) {
            if (Characters.checkIfContainsMandatoryCharacters(password, passwordProperties)) {
                containsAllMandatoryCharacters = true;
            } else {
                password = checkMandatoryCharacters(password, passwordProperties);
            }
        }
        return String.valueOf(password);
    }

    private char [] checkMandatoryCharacters(char[] password, PasswordProperties passwordProperties){
        if(passwordProperties.isLowerCase() && !Characters.checkIfHaveAtLeastOneLowerCase(password)){
            password[secureRandom.nextInt(password.length)] = Characters.lowerCase[secureRandom.nextInt(Characters.lowerCase.length)];
        }
        if(passwordProperties.isUpperCase() && !Characters.checkIfHaveAtLeastOneUpperCase(password)){
            password[secureRandom.nextInt(password.length)] = Characters.upperCase[secureRandom.nextInt(Characters.upperCase.length)];
        }
        if(passwordProperties.isNumbers() && !Characters.checkIfHaveAtLeastOneNumericCase(password)){
            password[secureRandom.nextInt(password.length)] = Characters.numbers[secureRandom.nextInt(Characters.numbers.length)];
        }
        if(passwordProperties.isSymbols() && !Characters.checkIfHaveAtLeastOneSymbolCase(password)){
            password[secureRandom.nextInt(password.length)] = Characters.symbols[secureRandom.nextInt(Characters.symbols.length)];
        }
        return password;
    }

}
