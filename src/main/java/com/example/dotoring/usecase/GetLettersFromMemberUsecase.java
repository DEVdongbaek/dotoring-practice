package com.example.dotoring.usecase;

import com.example.dotoring.domain.Member;
import com.example.dotoring.domain.Room;
import com.example.dotoring.dto.LetterResponseDTO;
import com.example.dotoring.service.LetterManagementService;
import com.example.dotoring.service.MemberService;
import com.example.dotoring.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetLettersFromMemberUsecase {
    final private LetterManagementService letterManagementService;

    final private MemberService memberService;

    final private RoomService roomService;

    @Transactional
    public List<LetterResponseDTO> execute(Long userId, Long roomPK){
        Room room = roomService.findByRoomId(roomPK);

        Member user = memberService.getMember(userId);

        List<LetterResponseDTO> letterResponseDTOList = letterManagementService.getLettersByOne(user, room);

        return letterResponseDTOList;
    }
}
