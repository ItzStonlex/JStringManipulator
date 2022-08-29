package com.itzstonlex.stringmanipulator.token.processor;

import com.itzstonlex.stringmanipulator.StringManipulatorContext;
import com.itzstonlex.stringmanipulator.token.QueryTokenizer;
import com.itzstonlex.stringmanipulator.token.QueryTokensExecutor;
import com.itzstonlex.stringmanipulator.token.TokenType;
import com.itzstonlex.stringmanipulator.token.TokensProcessor;

import java.util.Collection;

public class TokenAdd extends TokensProcessor {

    public TokenAdd(QueryTokenizer tokenizer) {
        super("add", tokenizer);
    }

    @Override
    public void process(StringManipulatorContext context, QueryTokensExecutor executor) {
        StringBuilder valueBuilder = new StringBuilder();
        String token;

        while (!(token = super.nextToken()).equals("in")) {
            valueBuilder.append(toJavaObject(executor, token)).append(" ");
        }

        String objectInto = super.nextToken();

        if (isVarName(objectInto)) {
            TokenType<Collection<Object>> variable = executor.getVar(objectInto.substring(1));

            if (variable != null && variable.getType().equalsIgnoreCase("Collection")) {
                String value = valueBuilder.toString();

                variable.getValue().add(
                        value.isEmpty() ? "" : value.substring(0, value.length() - 1)
                );
            }
        }
    }

}
