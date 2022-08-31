package com.itzstonlex.stringmanipulator;

import com.itzstonlex.stringmanipulator.token.QueryTokensExecutor;
import com.itzstonlex.stringmanipulator.token.TokenType;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@RequiredArgsConstructor
public final class StringManipulatorSession {

    private final StringManipulatorContext context;

    private final List<StringQuery> sessionQueries = new ArrayList<>();

    public StringQuery makeQuery(String query) {
        return makeQuery(new StringBuilder(query));
    }

    public StringQuery makeQuery(StringBuilder query) {
        return new StringQuery(new StringBuilder(query));
    }

    public void submit(StringQuery stringQuery) {
        sessionQueries.add(stringQuery);
    }

    public StringQueryResponse commit() {
        Map<String, TokenType<?>> committedTokenTypes = new WeakHashMap<>();

        int executedQueries = sessionQueries.stream().mapToInt(StringQuery::getRepeat).sum();
        int flushedQueries = 0;

        StringBuilder consoleInput = new StringBuilder();

        for (StringQuery stringQuery : sessionQueries) {
            QueryTokensExecutor tokensExecutor = context.createTokensExecutor(stringQuery);

            for (int repeat = 0; repeat < stringQuery.getRepeat(); repeat++) {
                tokensExecutor.execute();
            }

            committedTokenTypes.putAll(tokensExecutor.getVariables());

            consoleInput.append(tokensExecutor.getConsoleInput());

            if (stringQuery.isResetOnCommit()) {
                flushedQueries++;
            }
        }

        sessionQueries.removeIf(StringQuery::isResetOnCommit);
        return new StringQueryResponse(committedTokenTypes, consoleInput.toString(), executedQueries, flushedQueries);
    }

}
