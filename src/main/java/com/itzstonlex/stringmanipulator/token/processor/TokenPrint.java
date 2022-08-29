package com.itzstonlex.stringmanipulator.token.processor;

import com.itzstonlex.stringmanipulator.StringManipulatorContext;
import com.itzstonlex.stringmanipulator.token.QueryTokenizer;
import com.itzstonlex.stringmanipulator.token.QueryTokensExecutor;
import com.itzstonlex.stringmanipulator.token.TokensProcessor;

public class TokenPrint extends TokensProcessor {

    public TokenPrint(QueryTokenizer tokenizer) {
        super("print", tokenizer);
    }

    @Override
    public void process(StringManipulatorContext context, QueryTokensExecutor executor) {
        StringBuilder messageBuilder = new StringBuilder();
        String token;

        while (!(token = nextToken()).equals("]")) {

            Object value = isVarName(token)
                    ? executor.getVar(token.substring(1)).getValue()
                    : toJavaObject(executor, token);

            messageBuilder.append(value).append(" ");
        }

        String message = messageBuilder.toString();
        System.out.println(message.isEmpty() ? "" : message.substring(0, message.length() - 1));
    }

}
