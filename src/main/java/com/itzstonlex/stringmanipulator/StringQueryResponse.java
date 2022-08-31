package com.itzstonlex.stringmanipulator;

import com.itzstonlex.stringmanipulator.token.TokenType;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class StringQueryResponse {

    private final Map<String, TokenType<?>> committedTokensTypes;

    @Getter
    private final String consoleInput;

    @Getter
    private final int executedQueries, flushedQueries;

    public Collection<TokenType<?>> varTokens() {
        return committedTokensTypes.values();
    }

    @SuppressWarnings("unchecked")
    public <T> T var(@NonNull String name) {
        TokenType<T> tokenType = (TokenType<T>) committedTokensTypes.get(name);

        if (tokenType == null) {
            throw new NullPointerException("token type");
        }

        return tokenType.getValue();
    }

}
