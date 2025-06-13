package com.example.playground.stable.mock.test;

import lombok.Data;

@Data
public class RecursiveDto {
    private String name;
    private RecursiveDto children;
}
