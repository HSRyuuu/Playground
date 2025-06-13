package com.example.playground.stable.mock.generator;

import com.example.playground.stable.mock.util.MockContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

public class ObjectValueGenerator implements MockValueGenerator{

    private final List<MockValueGenerator> generators;

    public ObjectValueGenerator(List<MockValueGenerator> generators) {
        this.generators = generators;
    }

    @Override
    public boolean supports(Field field) {
        return true;
    }

    @Override
    public Object generate(Field field, MockContext context) {
        Class<?> type = field.getType();
        return generate(type, context);
    }

    public <T> T generate(Class<T> clazz, MockContext context) {
        if (!context.canGoDeeper()) return null;
        context.enter();

        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            T instance = constructor.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                for (MockValueGenerator gen : generators) {
                    if (gen.supports(field)) {
                        Object value = gen.generate(field, context);
                        field.set(instance, value);
                        break;
                    }
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate: " + clazz, e);
        } finally {
            context.exit();
        }
    }
}
