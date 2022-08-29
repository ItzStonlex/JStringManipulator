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

    @SuppressWarnings("unchecked")
    @Override
    public void process(StringManipulatorContext context, QueryTokensExecutor executor) {
        String valueToAdd = nextToken();

        if (super.nextToken().equals("into")) {

            String objectInto = super.nextToken();

            if (isVarName(objectInto)) {
                TokenType variable = executor.getVar(objectInto.substring(1));

                if (variable != null && variable.getType().equalsIgnoreCase("Collection")) {

                    ((Collection<Object>) variable.getValue()).add(
                            toJavaObject(executor, valueToAdd)
                    );
                }
            }
        }
    }
}