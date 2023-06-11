package com.example.dotoring.usecase;

import com.example.dotoring.domain.Room;
import com.example.dotoring.dto.RoomResponseDTO;
import com.example.dotoring.mapper.RoomMapper;
import com.example.dotoring.service.LetterManagementService;
import com.example.dotoring.service.MemberService;
import com.example.dotoring.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetRoomsFromMemberUsecase {

    final private RoomService roomService;

    @Transactional
    public List<RoomResponseDTO> execute(Long userId){
        List<Room> room = roomService.findAllByUserId(userId);

        List<RoomResponseDTO> responseDTOList = RoomMapper.INSTANCE.toDTOs(room);

        return responseDTOList;
    }
}
