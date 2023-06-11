package com.example.dotoring.usecase;

import com.example.dotoring.domain.Letter;
import com.example.dotoring.domain.Member;
import com.example.dotoring.domain.Room;
import com.example.dotoring.dto.LetterRequestDTO;
import com.example.dotoring.dto.LetterResponseDTO;
import com.example.dotoring.mapper.LetterMapper;
import com.example.dotoring.service.LetterManagementService;
import com.example.dotoring.service.MemberService;
import com.example.dotoring.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateLetterMember2MemberUsecase {

    final private LetterManagementService letterManagementService;

    final private MemberService memberService;

    final private RoomService roomService;

    @Transactional
    public LetterResponseDTO execute(LetterRequestDTO letterRequestDTO, Long userId, Long otherUserId){

        Member user = memberService.getMember(userId);

        Member otherUser = memberService.getMember(otherUserId);

        Room room = roomService.findOrCreateRoom(user, otherUser);

        Letter letter = letterManagementService.sendLetterFromOut(letterRequestDTO, user, otherUser, room);

        LetterResponseDTO letterResponseDTO = LetterMapper.INSTANCE.toDTO(letter, user);

        return letterResponseDTO;
    }
}
