package com.example.dotoring.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Note {

    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ColumnDefault("false")
    private boolean isDeletedByMento; // Mento에게 삭제 되었는지
    @ColumnDefault("fase")
    private boolean isDeletedByMenti; // Menti에게 삭제 되었는지
    @ColumnDefault("false")
    private boolean isViewedByMento; // Mento가 Note를 봤는지
    @ColumnDefault("false")
    private boolean isViewedByMenti; // Menti가 Note를 봤는지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mento_id")
    private Mento mento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menti_od")
    private Menti menti;

    @Builder
    public Note(String content, Mento mento, Menti menti){
        this.content = content;
        this.mento = mento;
        this.menti = menti;
    }

    // Mento, Menti 둘 다 삭제하면 아예 삭제
    public boolean isDeleted(){
        return this.isDeletedByMento&&this.isDeletedByMenti;
    }

    // Mento가 삭제를 했을 때
    public void deletedByMento(){
        this.isDeletedByMento = true;
    }

    // Menti가 삭제를 했을 때
    public void deletedByMenti(){
        this.isDeletedByMenti = true;
    }

    // Mento가 노트를 봤을 때
    public void viewedByMento(){
        this.isViewedByMento = true;
    }

    // Menti가 노트를 봤을 때
    public void viewedByMenti(){
        this.isViewedByMenti = true;
    }


}
