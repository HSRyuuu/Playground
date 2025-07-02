package com.example.playground.spring.jpa.dto;

import com.example.playground.spring.jpa.entity.AppUser;
import lombok.*;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    private UUID id;
    private String name;
    private UUID groupId;
    private String groupName;

    public static UserDto from(AppUser user) {
            return UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .groupId(user.getGroup().getId())
                    .groupName(user.getGroup().getName())
                    .build();
    }
}
