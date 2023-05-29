package com.example.dotoring.dto;

import com.example.dotoring.domain.Mento;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class NoteDto {
    @Getter
    @Setter
    public static class Request {

        private String username;
        private String password;

        public Mento toEntity(){
            return Mento.builder()
                    .username(username)
                    .password(password)
                    .build();
        }
    }


    @Getter
    @Setter
    @Builder
    public static class Response {

        private Long id;
        private String password;
        private List<String> noteMenties;


    }
}
