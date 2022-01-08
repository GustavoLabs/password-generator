package com.psw.generator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordProperties {
    private int length;
    private boolean isLowerCase;
    private boolean isUpperCase;
    private boolean isNumbers;
    private boolean isSymbols;
}
