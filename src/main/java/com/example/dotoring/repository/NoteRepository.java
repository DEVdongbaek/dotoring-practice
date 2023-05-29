package com.example.dotoring.repository;

import com.example.dotoring.domain.Menti;
import com.example.dotoring.domain.Mento;
import com.example.dotoring.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByMentoAndMenti(Mento mento, Menti menti);
}
