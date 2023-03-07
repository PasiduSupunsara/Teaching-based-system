package com.example.Teaching_based_system.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AssesmentDTO {
    public int assid;
    public int cid;
    public String assesmentname;
    public String details;
    private String duedate;
}
