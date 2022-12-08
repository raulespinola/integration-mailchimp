package com.trio.challenge.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetail implements Serializable {
    private ErrorDetailCode code;
    private String detail;
}
