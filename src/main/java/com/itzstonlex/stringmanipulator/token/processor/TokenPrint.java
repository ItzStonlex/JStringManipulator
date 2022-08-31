package com.itzstonlex.stringmanipulator.token.processor;

import com.itzstonlex.stringmanipulator.StringManipulatorContext;
import com.itzstonlex.stringmanipulator.token.QueryTokenizer;
import com.itzstonlex.stringmanipulator.token.QueryTokensExecutor;
import com.itzstonlex.stringmanipulator.token.TokensProcessor;

public class TokenPrint extends TokensProcessor {

    public TokenPrint(QueryTokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void process(StringManipulatorContext context, QueryTokensExecutor executor) {
        executor.getConsoleInput().append(buildMessage(context, executor, "]"))
                .append("\n");
    }

}
