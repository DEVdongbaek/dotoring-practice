package com.example.dotoring.dto;

import com.example.dotoring.domain.Letter;
import com.example.dotoring.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LettersAndMemberDTO {

    private List<Letter> letterList;

    private Member member;
}
