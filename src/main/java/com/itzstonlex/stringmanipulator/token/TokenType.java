package com.itzstonlex.stringmanipulator.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public final class TokenType<T> {

    private final String name, type;
    private T value;
}
