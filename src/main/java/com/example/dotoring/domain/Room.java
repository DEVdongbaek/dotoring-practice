package com.example.dotoring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {
    @Id
    @Column(name = "letter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @Builder.Default
    private List<Letter> letterList = new ArrayList<>();

    @ManyToOne
    private Member writer;

    @ManyToOne
    private Member receiver;

    @CreatedDate
    private LocalDateTime createAt;

    @CreatedDate
    private LocalDateTime updatedAt;

    // 최신화
    public void updateTime(){
        this.updatedAt = LocalDateTime.now();
    }

    // letterList에 추가하기
    public void addLetterList(Letter letter){
        this.letterList.add(letter);
    }
}
