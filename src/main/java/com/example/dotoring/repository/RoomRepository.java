package com.example.dotoring.repository;

import com.example.dotoring.domain.Member;
import com.example.dotoring.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByWriterOrReceiver(Member writer, Member receiver);

    Optional<Room> findByWriterAndReceiver(Member writer, Member receiver);
}
