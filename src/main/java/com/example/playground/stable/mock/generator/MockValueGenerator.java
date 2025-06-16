package com.example.playground.stable.mock.generator;

import com.example.playground.stable.mock.util.MockContext;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public interface MockValueGenerator {
    boolean supports(Class<?> rawType, Type genericType);
    Object generate(Class<?> rawType, Type genericType, MockContext context);
}