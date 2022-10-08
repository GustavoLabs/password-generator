package com.psw.generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PasswordDto {
    private int length;
    private boolean isLowerCase;
    private boolean isUpperCase;
    private boolean isNumbers;
    private boolean isSymbol;
}
