package com.example.dotoring.dto;

import com.example.dotoring.domain.Menti;
import lombok.Builder;

import java.util.List;

// new 통한 Dto 객체 생성
public class MentiResponseDto {
    private Long id;
    private String username;
    private String password;
    private List<String> noteMentoes;

    // 이렇게 new를 통해 Dto 객체를 생성했을 때 장, 단점은??
    public MentiResponseDto toDto(Menti menti) {
        this.id = menti.getId();
        this.username = menti.getUsername();
        this.password = menti.getPassword();
        this.noteMentoes = menti.getNoteMentoes();

        return this;
    }
}
