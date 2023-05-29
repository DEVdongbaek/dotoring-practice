package com.example.dotoring.repository;

import com.example.dotoring.domain.Menti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentiRepository extends JpaRepository<Menti, Long> {
}
