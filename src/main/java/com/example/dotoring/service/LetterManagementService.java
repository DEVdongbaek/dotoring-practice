package com.example.dotoring.service;

import com.example.dotoring.domain.Letter;
import com.example.dotoring.domain.Member;
import com.example.dotoring.domain.Room;
import com.example.dotoring.dto.LetterRequestDTO;
import com.example.dotoring.dto.LetterResponseDTO;
import com.example.dotoring.mapper.LetterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LetterManagementService {

    private final RoomService roomService;

    private final MemberService memberService;
    
    // 밖에서 쪽지 보내기
    @Transactional
    public Letter sendLetterFromOut(LetterRequestDTO letterRequestDTO, Member user, Member otherUser, Room room){
        Letter letter = LetterMapper.INSTANCE.toEntity(letterRequestDTO, user);

        room.addLetterList(letter);

        return letter;
    }
    
    // 안에서 쪽지 보내기
    @Transactional
    public Letter sendLetterFromIn(LetterRequestDTO letterRequestDTO, Member user, Room room){
        Letter letter = LetterMapper.INSTANCE.toEntity(letterRequestDTO, user);

        room.addLetterList(letter);

        return letter;
    }

    @Transactional(readOnly = true)
    public List<Room> getLettersByGroup(Long userId){
        List<Room> roomList = roomService.findAllByUserId(userId);
        return roomList;
    }

    @Transactional(readOnly = true)
    public List<LetterResponseDTO> getLettersByOne(Member user, Room room) {

        List<Letter> letters = room.getLetterList();

        List<LetterResponseDTO> letterResponseDTOS = LetterMapper.INSTANCE.toDTOs(letters, user);

        return letterResponseDTOS;
    }
}
