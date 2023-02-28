package com.example.Teaching_based_system.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseDTO {
    private String accessToken;
    private String refreshToken ;
    private String error;
    private String message;
    private String role;
    private int uid;

    public ResponseDTO(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
