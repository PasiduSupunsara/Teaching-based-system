package com.example.Teaching_based_system.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseDTO {
    private String token;
    private String error;
    private String message;


}
