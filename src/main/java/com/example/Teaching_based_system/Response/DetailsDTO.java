package com.example.Teaching_based_system.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DetailsDTO{

    private String token;
    private String error;
    private String message;
    private List<ViewDTO> userDetails;




}
