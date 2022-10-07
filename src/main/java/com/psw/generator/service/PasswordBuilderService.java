package com.psw.generator.service;

import com.psw.generator.model.PasswordEnum;
import com.psw.generator.model.PasswordProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class PasswordBuilderService {

    private final SecureRandom secureRandom = new SecureRandom();

    public String passwordBuilder(PasswordProperties passwordProperties) {
        return getPassword(passwordProperties);
    }

    private String getPassword(PasswordProperties passwordProperties) {
        List<Character> password = new ArrayList<>();
        for (PasswordEnum charEnum : passwordProperties.getEnums()) {
            int randomChar = secureRandom.nextInt(charEnum.getCharacters().length);
            password.add(charEnum.getCharacters()[randomChar]);
        }
        return completePassword(password, passwordProperties.getEnums(), passwordProperties.getLength());
    }

    private String completePassword(List<Character> password, List<PasswordEnum> enums, int passwordMaxSize) {
        for (int index = password.size(); index < passwordMaxSize; index++) {
            int randomEnum = secureRandom.nextInt(enums.size());
            int randomChar = secureRandom.nextInt(enums.get(randomEnum).getCharacters().length);
            password.add(enums.get(randomEnum).getCharacters()[randomChar]);
        }
        return shufflePassword(password);
    }

    private String shufflePassword(List<Character> password) {
        System.out.println("Without shuffle: " + password);
        StringBuilder finalPassword = new StringBuilder();
        Collections.shuffle(password);
        System.out.println("With shuffle: " + password);
        for (Character c : password) {
            finalPassword.append(c);
        }
        return finalPassword.toString();
    }

}
