package com.trio.challenge.integration.client.trio.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Root{
    private LocalDateTime createdAt;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private String id;
}

