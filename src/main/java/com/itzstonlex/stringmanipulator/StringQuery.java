package com.itzstonlex.stringmanipulator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringQuery {

    private final StringBuilder query;

    @Getter
    private int repeat;

    @Getter
    private boolean resetOnCommit;

    /* Default Settings */ {

        repeat = 1;
        resetOnCommit = true;
    }

    public StringQuery next(String query) {
        this.query.append("\n").append(query);
        return this;
    }

    public StringQuery repeat(int count) {
        repeat = count;
        return this;
    }

    public StringQuery resetOnCommit(boolean flag) {
        resetOnCommit = flag;
        return this;
    }

    @Override
    public String toString() {
        return query.toString();
    }

    public final void commitSingle(StringManipulatorContext context) {
        StringManipulatorSession session = context.createSession();

        session.submit(this);
        session.commit();
    }

}
