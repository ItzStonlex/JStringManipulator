package com.itzstonlex.stringmanipulator.token;

import com.itzstonlex.stringmanipulator.StringManipulatorContext;
import com.itzstonlex.stringmanipulator.token.processor.TokenVar;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class TokensProcessor {

    private final QueryTokenizer tokenizer;

    public abstract void process(StringManipulatorContext context, QueryTokensExecutor executor);

    protected String nextToken() {
        return tokenizer.nextToken();
    }

    protected boolean isObject(String token) {
        return token.startsWith("@");
    }

    protected boolean isVarName(String token) {
        return token.startsWith("$");
    }

    protected boolean isString(String token) {
        return token.startsWith("'") && token.endsWith("'");
    }

    protected boolean isBoolean(String token) {
        return token.equals("true") || token.equals("false");
    }

    protected boolean isNumber(String token) {
        try {
            Long.parseLong(token);
            return true;
        }
        catch (NumberFormatException exception) {
            return false;
        }
    }

    protected boolean isDouble(String token) {
        try {
            Double.parseDouble(token);
            return true;
        }
        catch (NumberFormatException exception) {
            return false;
        }
    }

    protected Object toJavaObject(QueryTokensExecutor executor, String token) {
        if (isString(token)) {
            return token.substring(1, token.length() - 1);
        }

        if (isBoolean(token)) {
            return Boolean.parseBoolean(token);
        }

        if (isNumber(token)) {
            return Long.parseLong(token);
        }

        if (isDouble(token)) {
            return Double.parseDouble(token);
        }

        return executor.getVar(token).getValue();
    }

    protected String buildMessage(StringManipulatorContext context, QueryTokensExecutor executor, String endpointToken) {
        StringBuilder messageBuilder = new StringBuilder();

        String token;
        while (!(token = nextToken()).equals(endpointToken)) {

            Object value = isVarName(token)
                    ? executor.getVar(token.substring(1)).getValue()
                    : !isObject(token)
                        ? toJavaObject(executor, token)
                        : context.<TokenVar>peekProcessor(tokenizer, "new").createObject(token);

            messageBuilder.append(value).append(" ");
        }

        String message = messageBuilder.toString();

        return message.isEmpty() ? "" : message.substring(0, message.length() - 1);
    }
}
