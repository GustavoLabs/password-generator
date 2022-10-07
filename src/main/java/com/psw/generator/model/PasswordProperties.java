package com.psw.generator.model;

import com.psw.generator.exception.InvalidOperationException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.psw.generator.model.PasswordEnum.*;
import static com.psw.generator.util.PasswordGlobalProperties.MAX_PSW_LENGTH;
import static com.psw.generator.util.PasswordGlobalProperties.MIN_PSW_LENGTH;

@Data
public class PasswordProperties {
    private List<PasswordEnum> enums;
    private int length;

    public PasswordProperties(PasswordDto dto) {
        this.length = Math.max(dto.getLength(), MIN_PSW_LENGTH);
        this.length = Math.min(this.length, MAX_PSW_LENGTH);
        enums = new ArrayList<>();
        if (dto.isLowerCase()) enums.add(LOWER_CASE);
        if (dto.isUpperCase()) enums.add(UPPER_CASE);
        if (dto.isNumbers()) enums.add(NUMERIC);
        if (dto.isSymbol()) enums.add(SYMBOL);
        if (enums.size() == 0) throw new InvalidOperationException("You must choose at least one type of character");
    }
}
