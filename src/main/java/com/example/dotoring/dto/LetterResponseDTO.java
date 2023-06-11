package com.example.dotoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LetterResponseDTO {

    private Long id;

    private String nickname;

    private String content;

    private boolean writer;

}
