package com.example.playground.stable.mock;

import com.example.playground.stable.mock.generator.*;
import com.example.playground.stable.mock.util.MockContext;

import java.util.*;

public class MockDataCreator {

    private final List<MockValueGenerator> generators;
    private final ObjectValueGenerator objectGenerator;
    public MockDataCreator() {
        this.generators = new ArrayList<>();
        this.generators.add(new PrimitiveValueGenerator());
        this.generators.add(new EnumValueGenerator());
        this.generators.add(new ListValueGenerator(generators));
        this.objectGenerator = new ObjectValueGenerator(generators);
        this.generators.add(objectGenerator);
    }

    public <T> T createMock(Class<T> clazz){
        return objectGenerator.generate(clazz, new MockContext(3));
    }
}
