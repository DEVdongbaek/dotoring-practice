package com.example.dotoring.mapper;

import com.example.dotoring.domain.Letter;
import com.example.dotoring.domain.Member;
import com.example.dotoring.dto.LetterRequestDTO;
import com.example.dotoring.dto.LetterResponseDTO;
import com.example.dotoring.dto.LettersAndMemberDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LetterMapper {
    // 매퍼 클래스에서 MentoMapper를 찾을 수 있도록 하는 코드
    LetterMapper INSTANCE = Mappers.getMapper(LetterMapper.class);

    // LetterRequestDto -> Letter 매핑
    @Mapping(source = "member", target = "writer")
    Letter toEntity(LetterRequestDTO letterRequestDTO, Member member);

    // List<Room> -> List<RoomResponseDto> 매핑, 이 때 반드시 Room -> RoomResponseDto 메서드가 먼저 있어야 한다.
    ArrayList<LetterResponseDTO> toDTOs(List<Letter> letter, @Context Member member);

    @Mapping(source = "letter.writer.username", target = "nickname")
    @Mapping(source = "letter.writer.id", target = "id")
    @Mapping(source = "letter.writer.id", target = "writer", qualifiedByName = "map")
    LetterResponseDTO toDTO(Letter letter, @Context Member member);

    // 메서드는 source를 인자로 받는다.
    @Named("map")
    default Boolean map (Long id, @Context Member member){
        return id.equals(member.getId());
    }
}
