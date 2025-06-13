package com.example.playground.stable.mock.generator;

import com.example.playground.stable.mock.util.MockContext;

import java.lang.reflect.Field;

public class EnumValueGenerator implements MockValueGenerator {
    @Override
    public boolean supports(Field field) {
        return field.getType().isEnum();
    }

    @Override
    public Object generate(Field field, MockContext mockContext) {
        Object[] constants = field.getType().getEnumConstants();
        return constants.length > 0 ? constants[0] : null;
    }
}
