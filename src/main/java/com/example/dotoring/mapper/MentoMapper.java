package com.example.dotoring.mapper;

import com.example.dotoring.domain.Mento;
import com.example.dotoring.dto.MentoRequestDto;
import com.example.dotoring.dto.MentoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MentoMapper {
    // 매퍼 클래스에서 MentoMapper를 찾을 수 있도록 하는 코드
    MentoMapper INSTANCE = Mappers.getMapper(MentoMapper.class);

    // MentoRequestDto -> Mento 매핑
    Mento toEntity(MentoRequestDto mentoRequestDto);
    
    // Mento -> MentoResponseDto 매핑
    MentoResponseDto toDto(Mento mento);

}
