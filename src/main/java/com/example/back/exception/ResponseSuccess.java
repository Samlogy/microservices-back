package com.example.back.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseSuccess {
    private boolean success = true;
    private String message;
    private Object validations;
}
