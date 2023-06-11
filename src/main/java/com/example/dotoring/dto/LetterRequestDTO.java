package com.example.dotoring.dto;

import com.example.dotoring.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LetterRequestDTO {

    private String content;

    private Member writer;
}
