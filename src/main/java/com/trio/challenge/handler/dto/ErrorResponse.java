package com.trio.challenge.handler.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse implements Serializable {
    private ErrorCode errorCode;
    private String message;
    private List<ErrorDetail> details;
}
