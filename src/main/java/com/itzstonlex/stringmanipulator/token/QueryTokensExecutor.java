package com.itzstonlex.stringmanipulator.token;

import com.itzstonlex.stringmanipulator.StringManipulatorContext;
import com.itzstonlex.stringmanipulator.StringQuery;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.WeakHashMap;

@RequiredArgsConstructor
public class QueryTokensExecutor {

    private final StringManipulatorContext context;
    private final StringQuery query;

    @Getter
    private final StringBuilder consoleInput = new StringBuilder();

    @Getter
    private final Map<String, TokenType<?>> variables = new WeakHashMap<>();

    private QueryTokenizer newTokenizer() {
        return new QueryTokenizer(query);
    }

    @SuppressWarnings("unchecked")
    public <T> TokenType<T> getVar(String name) {
        return (TokenType<T>) variables.get(name);
    }

    public void addVar(String name, TokenType<?> value) {
        variables.put(name, value);
    }

    public <T> void addVar(String name, String type, T value) {
        addVar(name, new TokenType<>(name, type, value));
    }

    public void execute() {
        QueryTokenizer tokenizer = newTokenizer();

        while (tokenizer.hasMoreTokens()) {
            String nextToken = tokenizer.nextToken();

            TokensProcessor tokensProcessor = context.peekProcessor(tokenizer, nextToken);

            if (tokensProcessor != null) {
                tokensProcessor.process(context, this);
            }
        }
    }

}
