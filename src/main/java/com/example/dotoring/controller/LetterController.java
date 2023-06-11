package com.example.dotoring.controller;

import com.example.dotoring.dto.LetterRequestDTO;
import com.example.dotoring.dto.LetterResponseDTO;
import com.example.dotoring.dto.RoomResponseDTO;
import com.example.dotoring.usecase.CreateLetterFromRoomUsecase;
import com.example.dotoring.usecase.CreateLetterMember2MemberUsecase;
import com.example.dotoring.usecase.GetLettersFromMemberUsecase;
import com.example.dotoring.usecase.GetRoomsFromMemberUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LetterController {

    private final CreateLetterMember2MemberUsecase createLetterMember2MemberUsecase;

    private final CreateLetterFromRoomUsecase createLetterFromRoomUsecase;

    private final GetRoomsFromMemberUsecase getRoomsFromMemberUsecase;

    private final GetLettersFromMemberUsecase getLettersFromMemberUsecase;

    @PostMapping("api/letterFromOut/{otherUserId}")
    public void sendLetterFromOut(@RequestBody LetterRequestDTO letterRequestDTO, Long userId, Long otherUserId){
        createLetterMember2MemberUsecase.execute(letterRequestDTO, userId, otherUserId);
    }

    @PostMapping("api/letterFromInd/{roomId}")
    public void sendLetterFromIn(@RequestBody LetterRequestDTO letterRequestDTO, Long userId, Long roomId){

        createLetterFromRoomUsecase.execute(letterRequestDTO, userId, roomId);
    }

    @GetMapping("api/letter/members")
    public List<RoomResponseDTO> getRooms(Long userId){
        return getRoomsFromMemberUsecase.execute(userId);
    }

    @GetMapping("api/letter/roomPK")
    public List<LetterResponseDTO> getLetters(Long roomPK, Long userId){
        return getLettersFromMemberUsecase.execute(roomPK, userId);
    }
}
