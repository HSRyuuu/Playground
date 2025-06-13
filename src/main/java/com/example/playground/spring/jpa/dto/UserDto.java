package com.example.playground.spring.jpa.dto;

import com.example.playground.stable.mock.test.MockTestDto;
import lombok.*;

import java.util.List;
import java.util.UUID;
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private UUID id;
    private List<MockTestDto> test;
}
