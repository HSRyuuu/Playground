package com.example.playground.stable.mock.generator;

import com.example.playground.stable.mock.util.MockContext;

import java.lang.reflect.Field;

public interface MockValueGenerator {
    boolean supports(Field field);
    Object generate(Field field, MockContext context);
}
