package com.workintech.spring17challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Grade {
    private int coefficient;
    private String note;
}
