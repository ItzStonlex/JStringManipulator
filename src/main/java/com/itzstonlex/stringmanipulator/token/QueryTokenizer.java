package com.itzstonlex.stringmanipulator.token;

import com.itzstonlex.stringmanipulator.StringQuery;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

import java.util.StringTokenizer;

@RequiredArgsConstructor
public class QueryTokenizer {

    @Delegate
    private final StringTokenizer tokenizer;

    public QueryTokenizer(StringQuery stringQuery) {
        this.tokenizer = new StringTokenizer(stringQuery.toString());
    }
}
