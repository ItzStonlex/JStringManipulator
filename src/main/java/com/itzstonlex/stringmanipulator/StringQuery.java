package com.itzstonlex.stringmanipulator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringQuery {

    private final StringBuilder query;

    private String condition, exception;

    public StringQuery next(String query) {
        this.query.append("\n").append(query);
        return this;
    }

    public StringQuery condition(String conditionQuery) {
        this.condition = conditionQuery;
        return this;
    }

    public StringQuery exception(String exceptionQuery) {
        this.exception = exceptionQuery;
        return this;
    }

    @Override
    public String toString() {
        return query.toString();
    }

}
