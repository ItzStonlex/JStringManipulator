package com.itzstonlex.stringmanipulator.token.processor;

import com.itzstonlex.stringmanipulator.StringManipulatorContext;
import com.itzstonlex.stringmanipulator.token.QueryTokenizer;
import com.itzstonlex.stringmanipulator.token.QueryTokensExecutor;
import com.itzstonlex.stringmanipulator.token.TokenType;
import com.itzstonlex.stringmanipulator.token.TokensProcessor;

public class TokenSet extends TokensProcessor {

    public TokenSet(QueryTokenizer tokenizer) {
        super("set", tokenizer);
    }

    @Override
    public void process(StringManipulatorContext context, QueryTokensExecutor executor) {
        String varName = super.nextToken();

        if (isVarName(varName)) {
            TokenType<Object> tokenType = executor.getVar(varName.substring(1));

            if (super.nextToken().equals("=")) {
                tokenType.setValue(buildMessage(context, executor, "]"));
            }
        }
    }
}
