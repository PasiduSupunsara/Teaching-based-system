package com.example.Teaching_based_system.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DetailDTO {
    private String token;
    private String error;
    private String message;
    private ViewDTO userDetails;
}
