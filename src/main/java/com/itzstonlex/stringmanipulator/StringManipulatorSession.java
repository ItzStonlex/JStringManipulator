package com.itzstonlex.stringmanipulator;

import com.itzstonlex.stringmanipulator.token.QueryTokensExecutor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    public void execute(StringQuery stringQuery) {
        sessionQueries.add(stringQuery);
    }

    public void reset() {
        sessionQueries.clear();
    }

    public void commit() {
        for (StringQuery stringQuery : sessionQueries) {
            context.createTokensExecutor(stringQuery).execute();
        }

        reset();
    }

}
