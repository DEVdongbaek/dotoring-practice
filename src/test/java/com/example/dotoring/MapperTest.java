package com.example.dotoring;

import com.example.dotoring.domain.Letter;
import com.example.dotoring.domain.Member;
import com.example.dotoring.domain.Room;
import com.example.dotoring.dto.LetterRequestDTO;
import com.example.dotoring.dto.LetterResponseDTO;
import com.example.dotoring.dto.RoomResponseDTO;
import com.example.dotoring.mapper.RoomMapper;
import com.example.dotoring.service.LetterManagementService;
import com.example.dotoring.service.MemberService;
import com.example.dotoring.service.RoomService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest

public class MapperTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberService memberService;

    @Autowired
    private LetterManagementService letterManagementService;

    @Autowired
    private RoomService roomService;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void makeEntity() {
//        memberService.saveMember("영구");
//        memberService.saveMember("빡구");
//        memberService.saveMember("반갑구");
//        memberService.saveMember("놀라구");
//
//        em.flush();
//        em.clear();
    }

    @Test
    @Transactional
    public void send_messageFromOut_test1() throws Exception {
        // Letter 생성
        LetterRequestDTO letter = LetterRequestDTO.builder()
                .content("쪽지 내용이와요")
                .build();

        // Entity 생성
        Member member = memberService.saveMember("kevin");

        // 쪽지 보내기
        Letter ans = letterManagementService.sendLetterFromOut(letter, member, 1L);
        System.out.println("answer : " + ans.getId());
        System.out.println("answer : " + ans.getContent());
    }

    @Test
    @Transactional
    public void send_messageFromIn_test1() {
        // RequestDto -> Entity
        // Letter 생성
        LetterRequestDTO letter = LetterRequestDTO.builder()
                .content("쪽지 내용이와요")
                .build();

        // Entity 생성
        Member member = memberService.saveMember("kevin");
        
        // Room 생성
        roomService.findOrCreateRoom(member, memberService.getMember(1L));

        // 쪽지 보내기
        Letter ans = letterManagementService.sendLetterFromIn(letter, member, 1L);
        System.out.println("answer : " + ans.getId());
        System.out.println("answer : " + ans.getContent());
    }

    @Test
    @Transactional
    public void get_rooms(){
        // RequestDto -> Entity
        // Letter 생성
        LetterRequestDTO letter = LetterRequestDTO.builder()
                .content("쪽지 내용이와요")
                .build();

        // Entity 생성
        Member member = memberService.saveMember("kevin");

        // Room 생성
        Room room1 = roomService.findOrCreateRoom(member, memberService.getMember(2L));
        // 쪽지 보내기1
        letterManagementService.sendLetterFromIn(letter, member, 1L);

        Room room2 = roomService.findOrCreateRoom(member, memberService.getMember(3L));
        // 쪽지 보내기2
        letterManagementService.sendLetterFromIn(letter, member, 2L);

        Room room3 = roomService.findOrCreateRoom(member, memberService.getMember(4L));
        // 쪽지 보내기3
        letterManagementService.sendLetterFromIn(letter, member, 3L);

        List<Room> rooms = roomService.findAllByUserId(5L);

        List<RoomResponseDTO> responseDTO = RoomMapper.INSTANCE.toDTOs(rooms);

        for (RoomResponseDTO dto : responseDTO) {
            System.out.println("answer : "+ dto.getMemberPK());
            System.out.println("answer : "+ dto.getNickname());
            System.out.println("answer : "+ dto.getLastLetter());
        }
    }

    @Test
    @Transactional
    public void get_messages(){
        // RequestDto -> Entity
        // Letter 생성
        LetterRequestDTO letter1 = LetterRequestDTO.builder()
                .content("쪽지 내용이와요")
                .build();
        // Letter 생성
        LetterRequestDTO letter2 = LetterRequestDTO.builder()
                .content("쪽지 내용이와요")
                .build();
        // Letter 생성
        LetterRequestDTO letter3 = LetterRequestDTO.builder()
                .content("쪽지 내용이와요")
                .build();
        // Letter 생성
        LetterRequestDTO letter4 = LetterRequestDTO.builder()
                .content("쪽지 내용이와요")
                .build();
        // Letter 생성
        LetterRequestDTO letter5 = LetterRequestDTO.builder()
                .content("쪽지 내용이와요")
                .build();


        // Entity 생성
        Member member1 = memberService.saveMember("kevin");
        Member member2 = memberService.saveMember("pogba");

        // Room 생성
        Room room1 = roomService.findOrCreateRoom(member1, member2);

        // 쪽지 보내기
        letterManagementService.sendLetterFromIn(letter1, member1, 1L);
        letterManagementService.sendLetterFromIn(letter2, member2, 1L);
        letterManagementService.sendLetterFromIn(letter3, member1, 1L);
        letterManagementService.sendLetterFromIn(letter4, member2, 1L);
        Letter letter = letterManagementService.sendLetterFromIn(letter5, member1, 1L);

        // letter가 null인데?? 엥? -> member가 매핑이 안되어있잖아
        System.out.println("answer : "+ letter.getContent());

        List<LetterResponseDTO> lists = letterManagementService.getLettersByOne(1L, 1L);

        System.out.println("answer : "+ lists.size()); // 0 출력된다. -> 매핑이 안되고 있다는 것

        for (LetterResponseDTO dto : lists) {
            System.out.println("answer : "+ dto.getId());
            System.out.println("answer : "+ dto.getNickname());
            System.out.println("answer : "+ dto.isWriter());
        }
    }
}
