package com.example.Teaching_based_system.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Message1DTO {
    private int mid = 0;
    private int sid;
    private int cid;
    private String message;
    private String sender;
}
