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
        StringBuilder message = new StringBuilder();

        String token;
        while (!(token = nextToken()).equals("]")) {

            if (isVarName(token)) {
                message.append(executor.getVar(token.substring(1)).getValue());
            }
            else {
                message.append(toJavaObject(executor, token));
            }
        }

        System.out.println(message);
    }

}
