package com.example.Teaching_based_system.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class NotificationDTO {
    private int mid ;
    private String sname;
    private String message;
}
