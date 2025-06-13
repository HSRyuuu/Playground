package com.example.playground.stable.mock.generator;

import com.example.playground.stable.mock.util.MockContext;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Supplier;

public class PrimitiveValueGenerator implements MockValueGenerator {

    private static final Random RANDOM = new Random();
    private static final Map<Class<?>, Supplier<?>> SUPPLIERS = new HashMap<>();

    static {
        SUPPLIERS.put(String.class, () -> "mock_" + RANDOM.nextInt(1000));
        SUPPLIERS.put(int.class, () -> RANDOM.nextInt(100));
        SUPPLIERS.put(Integer.class, () -> RANDOM.nextInt(100));
        SUPPLIERS.put(long.class, () -> RANDOM.nextLong(1000));
        SUPPLIERS.put(Long.class, () -> RANDOM.nextLong(1000));
        SUPPLIERS.put(boolean.class, RANDOM::nextBoolean);
        SUPPLIERS.put(Boolean.class, RANDOM::nextBoolean);
        SUPPLIERS.put(double.class, () -> RANDOM.nextDouble(1000));
        SUPPLIERS.put(Double.class, () -> RANDOM.nextDouble(1000));
        SUPPLIERS.put(float.class, () -> RANDOM.nextFloat(1000));
        SUPPLIERS.put(Float.class,() -> RANDOM.nextFloat(1000));
        SUPPLIERS.put(UUID.class, UUID::randomUUID);
    }


    @Override
    public boolean supports(Field field) {
        return SUPPLIERS.containsKey(field.getType());
    }

    @Override
    public Object generate(Field field, MockContext mockContext) {
        Object value = SUPPLIERS.get(field.getType()).get();
        if (field.getType() == String.class) {
            return field.getName() + "_" + value;
        }
        return value;
    }
}
