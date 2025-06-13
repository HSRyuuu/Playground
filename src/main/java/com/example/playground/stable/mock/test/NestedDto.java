package com.example.playground.stable.mock.test;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class NestedDto {
    private Long id;
    private String name;
    private SampleEnum type;
}
