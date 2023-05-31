package com.example.dotoring.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentoResponseDto {
    private Long id;
    private String username;
    private String password;

}
