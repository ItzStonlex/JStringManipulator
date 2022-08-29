package com.itzstonlex.stringmanipulator.token.processor;

import com.itzstonlex.stringmanipulator.StringManipulatorContext;
import com.itzstonlex.stringmanipulator.token.QueryTokenizer;
import com.itzstonlex.stringmanipulator.token.QueryTokensExecutor;
import com.itzstonlex.stringmanipulator.token.TokenType;
import com.itzstonlex.stringmanipulator.token.TokensProcessor;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.function.Supplier;

public class TokenNew extends TokensProcessor {

    private final Hashtable<String, Supplier<Object>> objectsFactoryMap = new Hashtable<>();

    public TokenNew(QueryTokenizer tokenizer) {
        super("new", tokenizer);

        objectsFactoryMap.put("null", () -> null);
        objectsFactoryMap.put("Number", () -> 0L);
        objectsFactoryMap.put("Double", () -> 0D);
        objectsFactoryMap.put("String", String::new);
        objectsFactoryMap.put("Collection", ArrayList::new);
    }

    public final Object createObject(String name) {
        if (isObject(name)) {
            name = name.substring(1);
        }

        Supplier<Object> factory = objectsFactoryMap.get(name);

        if (factory == null) {
            return null;
        }

        return factory.get();
    }

    @Override
    public void process(StringManipulatorContext context, QueryTokensExecutor executor) {
        String objectKey = super.nextToken();

        if (!isObject(objectKey)) {
            throw new IllegalArgumentException("Object token must be starts with '@'");
        }

        String objectName = objectKey.substring(1);

        Supplier<Object> factory = objectsFactoryMap.get(objectName);

        if (factory == null) {
            throw new NullPointerException("Object '" + objectName + "' is not found!");
        }

        String name = "undefined";

        if (super.nextToken().equals("as")) {

            String varName = super.nextToken();

            if (isVarName(varName)) {
                name = varName.substring(1);
            }
        }

        executor.addVar(name, objectName, factory.get());
    }
}
