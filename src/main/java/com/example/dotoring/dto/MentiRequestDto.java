package com.example.dotoring.dto;

import com.example.dotoring.domain.Menti;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MentiRequestDto {

    private String username;
    private String password;

    public Menti toEntity(){
        return Menti.builder()
                .username(username)
                .password(password)
                .build();
    }
}
