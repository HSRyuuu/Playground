package com.example.playground.java.mock;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Supplier;

public class MockDataCreator {

    private static final Random RANDOM = new Random();
    private static final Map<Class<?>, Supplier<?>> RANDOM_SUPPLIERS = new HashMap<>();

    static {
        RANDOM_SUPPLIERS.put(String.class, () -> "mock_" + RANDOM.nextInt(1000));
        RANDOM_SUPPLIERS.put(int.class, () -> RANDOM.nextInt(100));
        RANDOM_SUPPLIERS.put(Integer.class, () -> RANDOM.nextInt(100));
        RANDOM_SUPPLIERS.put(long.class, RANDOM::nextLong);
        RANDOM_SUPPLIERS.put(Long.class, RANDOM::nextLong);
        RANDOM_SUPPLIERS.put(boolean.class, RANDOM::nextBoolean);
        RANDOM_SUPPLIERS.put(Boolean.class, RANDOM::nextBoolean);
        RANDOM_SUPPLIERS.put(double.class, RANDOM::nextDouble);
        RANDOM_SUPPLIERS.put(Double.class, RANDOM::nextDouble);
        RANDOM_SUPPLIERS.put(float.class, RANDOM::nextFloat);
        RANDOM_SUPPLIERS.put(Float.class, RANDOM::nextFloat);
        RANDOM_SUPPLIERS.put(UUID.class, UUID::randomUUID);
    }

    @SneakyThrows
    @SuppressWarnings("unckecked")
    public static <T> List<T> createMockList(Class<T> clazz, int size) {
        Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        List<T> mockDataList = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            mockDataList.add(createMock(clazz));
        }
        return mockDataList;
    }

    @SneakyThrows
    @SuppressWarnings("unckecked")
    public static <T> T createMock(Class<T> clazz) {
        Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);

        Field[] declaredFields = clazz.getDeclaredFields();
        T instance = declaredConstructor.newInstance();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object value = generateMockValue(field.getType(), field);
            field.set(instance, value);
        }
        return instance;
    }

    private static Object generateMockValue(Class<?> type, Field field) {
        Supplier<?> supplier = RANDOM_SUPPLIERS.get(type);
        if (supplier != null) {
            Object value = supplier.get();
            if (type == String.class) {
                return field.getName() + "_" + value;
            }
            return value;
        }
        if (List.class.isAssignableFrom(type)) {
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                Type actualType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
                if (actualType instanceof Class<?> genericClass) {

                    List<Object> list = new ArrayList<>();
                    for (int i = 0; i < 2; i++) { // 예: 2개 생성
                        list.add(generateMockValue(genericClass, null));
                    }
                    return list;
                }
            }
        }
        if (type.isEnum()) {
            Object[] enumConstants = type.getEnumConstants();
            return enumConstants.length > 0 ? enumConstants[0] : null;
        } else {
            return createMock(type);
        }
    }

}
