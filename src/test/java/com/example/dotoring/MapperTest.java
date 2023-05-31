package com.example.dotoring;

import com.example.dotoring.domain.Mento;
import com.example.dotoring.dto.MentoRequestDto;
import com.example.dotoring.dto.MentoResponseDto;
import com.example.dotoring.mapper.MentoMapper;
import com.example.dotoring.repository.MentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest

public class MapperTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MentoRepository repo;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void makeEntity() {
        Mento mento = Mento.builder()
                .username("kevin")
                .password("1234")
                .build();

        em.persist(mento);

        em.flush();

        em.clear();
    }

    @Test
    @Transactional
    public void mapper_test1() throws Exception {
        // Entity -> ResponseDto
        // Mento mento = em.find(Mento.class, 1L);
        Mento mento = repo.findById(1L).orElseThrow(Exception::new);
        MentoResponseDto mentoResponseDto = MentoMapper.INSTANCE.toDto(mento);
        System.out.println("testing ans : " + mento.getId());
        System.out.println("testing ans : " + mentoResponseDto.getUsername());
        System.out.println(mentoResponseDto.getId());

    }

    @Test
    public void mapper_test2(){
        // RequestDto -> Entity
        MentoRequestDto mentoRequestDto = MentoRequestDto.builder()
                .username("kingKevin")
                .password("11111")
                .build();

        Mento toMento = MentoMapper.INSTANCE.toEntity(mentoRequestDto);
        System.out.println("testing ans : " + toMento.getId());
        System.out.println("testing ans : " + toMento.getUsername());
    }
}
