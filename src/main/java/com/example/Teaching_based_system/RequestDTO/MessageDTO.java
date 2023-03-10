package com.example.Teaching_based_system.RequestDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class MessageDTO {
    private int mid = 0;
    private int sid;

    private String name;

    private String message;
}
