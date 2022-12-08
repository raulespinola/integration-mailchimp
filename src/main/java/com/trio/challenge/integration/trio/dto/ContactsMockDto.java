package com.trio.challenge.integration.trio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ContactsMockDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private LocalDateTime createdAt;
    @JsonProperty(value="firstName")
    private String firstName;
    @JsonProperty(value="lastName")
    private String lastName;
    @With
    private String email;
    private String avatar;
    private String id;
}

