package com.psw.generator.util;

import com.psw.generator.exception.InvalidOperationException;
import com.psw.generator.model.PasswordProperties;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Characters {
    public static final char[] lowerCase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final char[] upperCase = "ABCDEFGJKLMNPRSTUVWXYZ".toCharArray();
    public static final char[] numbers = "0123456789".toCharArray();
    public static final char[] symbols = "!@#$%¨&*()_+=[]{}".toCharArray();

    public static String getAllowedCharacters(PasswordProperties passwordProperties){
        StringBuilder allowedCharacters = new StringBuilder();
        if (passwordProperties.isLowerCase()) allowedCharacters.append(Characters.lowerCase);
        if (passwordProperties.isUpperCase()) allowedCharacters.append(Characters.upperCase);
        if (passwordProperties.isNumbers()) allowedCharacters.append(Characters.numbers);
        if (passwordProperties.isSymbols()) allowedCharacters.append(Characters.symbols);
        return allowedCharacters.toString();
    }

    public static boolean checkIfContainsMandatoryCharacters(char [] password, PasswordProperties passwordProperties){
        List<Boolean> checks = new ArrayList<>();
        if (passwordProperties.isLowerCase()) {
            checks.add(checkIfHaveAtLeastOneLowerCase(password));
        }
        if (passwordProperties.isUpperCase()) {
            checks.add(checkIfHaveAtLeastOneUpperCase(password));
        }
        if (passwordProperties.isNumbers()) {
            checks.add(checkIfHaveAtLeastOneNumericCase(password));
        }
        if (passwordProperties.isSymbols()) {
            checks.add(checkIfHaveAtLeastOneSymbolCase(password));
        }

        return !checks.contains(false);

    }

    public static boolean checkIfHaveAtLeastOneLowerCase(char [] passwordArray){
        String password = String.valueOf(passwordArray);
        for (char character : lowerCase) {
            if(password.contains(String.valueOf(character))) return true;
        }
        return false;
    }

    public static boolean checkIfHaveAtLeastOneUpperCase(char [] passwordArray){
        String password = String.valueOf(passwordArray);
        for (char character : upperCase) {
            if(password.contains(String.valueOf(character))) return true;
        }
        return false;
    }

    public static boolean checkIfHaveAtLeastOneNumericCase(char [] passwordArray){
        String password = String.valueOf(passwordArray);
        for (char character : numbers) {
            if(password.contains(String.valueOf(character))) return true;
        }
        return false;
    }

    public static boolean checkIfHaveAtLeastOneSymbolCase(char [] passwordArray){
        String password = String.valueOf(passwordArray);
        for (char character : symbols) {
            if(password.contains(String.valueOf(character))) return true;
        }
        return false;
    }

}
