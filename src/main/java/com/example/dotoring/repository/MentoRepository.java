package com.example.dotoring.repository;

import com.example.dotoring.domain.Menti;
import com.example.dotoring.domain.Mento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentoRepository extends JpaRepository<Mento, Long> {
}
