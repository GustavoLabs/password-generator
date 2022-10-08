package com.psw.generator.model;


import lombok.Getter;

public enum PasswordEnum {
    LOWER_CASE("abcdefghijklmnopqrstuvwxyz".toCharArray()),
    UPPER_CASE("ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()),
    NUMERIC("0123456789".toCharArray()),
    SYMBOL("!@#$%¨&*()_+=[]{}".toCharArray());

    @Getter
    char[] characters;

    PasswordEnum(char[] characters) {
        this.characters = characters;
    }
}
