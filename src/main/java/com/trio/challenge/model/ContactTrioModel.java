package com.trio.challenge.model;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class ContactTrioModel {
    	private String firstName;
        private String lastName;
        private String email;
}
