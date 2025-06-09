package com.example.playground.stable.restapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private HttpStatus status;
    private String errorMessage;
    private Object body;

    public static ApiResponse unexpectedError(){
        return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "unexpected error",null);
    }

}
