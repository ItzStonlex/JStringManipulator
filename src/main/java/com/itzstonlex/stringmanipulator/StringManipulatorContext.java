package com.itzstonlex.stringmanipulator;

import com.itzstonlex.stringmanipulator.token.QueryTokenizer;
import com.itzstonlex.stringmanipulator.token.QueryTokensExecutor;
import com.itzstonlex.stringmanipulator.token.TokensProcessor;
import com.itzstonlex.stringmanipulator.token.processor.TokenAdd;
import com.itzstonlex.stringmanipulator.token.processor.TokenNew;
import com.itzstonlex.stringmanipulator.token.processor.TokenPrint;
import com.itzstonlex.stringmanipulator.token.processor.TokenSet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public final class StringManipulatorContext {

    private final Map<String, Function<QueryTokenizer, TokensProcessor>> tokenProcessorMap = new ConcurrentHashMap<>();

    public StringManipulatorContext() {
        _initDefaultTokens();
    }

    private void _initDefaultTokens() {
        tokenProcessorMap.put("new", TokenNew::new);
        tokenProcessorMap.put("add", TokenAdd::new);
        tokenProcessorMap.put("print", TokenPrint::new);
        tokenProcessorMap.put("set", TokenSet::new);
    }

    private QueryTokensExecutor _initDefaultVariables(QueryTokensExecutor executor) {
        executor.addVar("PI", "Double", Math.PI);

        return executor;
    }

    @SuppressWarnings("unchecked")
    public <Processor extends TokensProcessor> Processor peekProcessor(QueryTokenizer tokenizer, String token) {
        return (Processor) tokenProcessorMap.getOrDefault(token, (f) -> null).apply(tokenizer);
    }

    public StringManipulatorSession createSession() {
        return new StringManipulatorSession(this);
    }

    public QueryTokensExecutor createTokensExecutor(StringQuery stringQuery) {
        return _initDefaultVariables(new QueryTokensExecutor(this, stringQuery));
    }
}
