package com.itzstonlex.stringmanipulator.token;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public final class TokenType<T> {

    private final String name, type;
    private final T value;
}
