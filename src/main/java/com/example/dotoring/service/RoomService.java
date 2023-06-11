package com.example.dotoring.service;

import com.example.dotoring.domain.Member;
import com.example.dotoring.domain.Room;
import com.example.dotoring.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final MemberService memberService;

    @Transactional
    public Room findOrCreateRoom(Member writer, Member receiver) {
        Optional<Room> room = roomRepository.findByWriterAndReceiver(writer, receiver);

        if (room.isPresent()) {
            Room roomEntity = room.get();
            roomEntity.updateTime();
            return roomEntity;
        }
            Room newRoom = makeRoomObject(writer, receiver);
            newRoom.updateTime();
            // 리턴에서 메서드를
            // return roomRepository.save(newRoom);
        return saveRoom(newRoom);
    }

    @Transactional(readOnly = true)
    public Room findByRoomId(Long roomId) {
        return roomRepository.findById(roomId).get();
    }

    @Transactional(readOnly = true)
    public List<Room> findAllByUserId(Long userId) {
        List<Room> rooms = roomRepository.findAllByWriterOrReceiver(memberService.getMember(userId), memberService.getMember(userId));
        return rooms;
    }

    private Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    private Room makeRoomObject(Member requestUser, Member otherUser){
        Room room = Room.builder()
                .writer(requestUser)
                .receiver(otherUser)
                .build();

        return room;
    }
}
