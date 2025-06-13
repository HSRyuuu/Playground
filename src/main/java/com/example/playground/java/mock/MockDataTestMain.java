package com.example.playground.java.mock;

import com.example.playground.spring.jpa.dto.UserDto;

import java.util.List;

public class MockDataTestMain {
    public static void main(String[] args) {

        List<UserDto> mockList = MockDataCreator.createMockList(UserDto.class, 10);
        System.out.println(mockList);
    }
}
