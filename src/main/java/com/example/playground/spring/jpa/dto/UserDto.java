package com.example.playground.spring.jpa.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {

    private String username;
    private UUID id;

   // private String groupName;
//    private String orderItemName;
//    private String paymentMethod;
}
