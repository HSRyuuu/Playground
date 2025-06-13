package com.example.playground.stable.mock.generator;


import com.example.playground.stable.mock.util.MockContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListValueGenerator implements MockValueGenerator {

    private final List<MockValueGenerator> generators;
    private final int listSize;
    public ListValueGenerator(List<MockValueGenerator> generators) {
        this.generators = generators;
        this.listSize = 2;
    }
    public ListValueGenerator(List<MockValueGenerator> generators, int listSize) {
        this.generators = generators;
        this.listSize = listSize;
    }

    @Override
    public boolean supports(Field field) {
        return List.class.isAssignableFrom(field.getType());
    }

    @Override
    public Object generate(Field field, MockContext context) {
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType pt) {
            Type actualType = pt.getActualTypeArguments()[0];
            if (actualType instanceof Class<?> genericClass) {
                List<Object> list = new ArrayList<>();

                for (int i = 0; i < listSize; i++) {
                    Object value = this.findGenerator(genericClass).generate(dummyField(genericClass), context);
                    list.add(value);
                }
                return list;
            }
        }
        return List.of();
    }

    private MockValueGenerator findGenerator(Class<?> clazz){
        for(MockValueGenerator generator : generators){
            if(generator.supports(this.dummyField(clazz))){
                return generator;
            }
        }
        throw new UnsupportedOperationException("No generator found for " + clazz);
    }

    private Field dummyField(Class<?> clazz){
        try{
            return Dummy.class.getDeclaredField("value");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static class Dummy {
        public Object value;
    }
}
