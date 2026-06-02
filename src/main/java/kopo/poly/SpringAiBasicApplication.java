package kopo.poly;

import kopo.poly.dto.OcrDTO;
import kopo.poly.dto.StudentDTO;
import kopo.poly.service.INlpService;
import kopo.poly.service.IOcrService;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class SpringAiBasicApplication implements CommandLineRunner {

    private final IOcrService ocrService; // 실습 #1 : OCR
    private final INlpService nlpService; // 실습 #2 : 자연어 처리
    private final IStudentService studentService; // 실습 : 학생 등록

    public static void main(String[] args) {
        SpringApplication.run(SpringAiBasicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("===== 자바 프로그래밍 시작! =====");

//        // ===== 실습 #1 : 이미지에서 글자 읽기 =====
//        OcrDTO rDTO = ocrService.getOcr("src/main/resources/image/sample01.png");
//        String text = rDTO.getResult();
//
//        log.info("===== 인식한 글자 =====");
//        log.info(text);
//
//        // ===== 실습 #2 : 가장 많이 쓰인 단어 찾기 =====
//
//        // 1단계 : 글에서 명사만 뽑기
//        List<String> nouns = nlpService.getNouns(text);
//        log.info("중복 제거 수행 전 단어 수 : {}", nouns.size());
//
//        // 2단계 : 중복된 단어 제거 (Set은 중복을 허용하지 않음)
//        Set<String> nounSet = new HashSet<>(nouns);
//        log.info("중복 제거 수행 후 단어 수 : {}", nounSet.size());
//
//        // 3단계 : 중복 제거된 단어별 등장 횟수 세기 → Map<String, Integer>
//        Map<String, Integer> wordCount = new HashMap<>();
//        for (String noun : nounSet) {
//            wordCount.put(noun, Collections.frequency(nouns, noun));
//        }
//
//        // 4단계 : 횟수 많은 순으로 정렬
//        Map<String, Integer> sorted = new LinkedHashMap<>();
//        wordCount.entrySet().stream()
//                .sorted((a, b) -> b.getValue() - a.getValue())
//                .forEach(e -> sorted.put(e.getKey(), e.getValue()));
//
//        // 결과 출력 (단어 : 횟수)
//        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
//            log.info("{} : {}", entry.getKey(), entry.getValue());
//        }

        StudentDTO pDTO; // 학생 등록/수정/삭제에 활용될 DTO
        List<StudentDTO> rList; // DB 조회 결과를 담음

        // ===== 실습 #1 : 학생 등록 =====
        pDTO = new StudentDTO();
        pDTO.setUserId("hglee5");
        pDTO.setUserName("이협건");
        pDTO.setEmail("hglee5@kopo.ac.kr");
        pDTO.setAddr("서울");

        rList = studentService.insertStudent(pDTO);

        log.info("===== 실습 #1 : 학생 등록 후 목록 =====");
        printStudentList(rList);

        // ===== 실습 #2 : 학생 수정 =====
        // 위에서 등록한 hglee5의 이름/이메일/주소를 변경
        pDTO = new StudentDTO();
        pDTO.setUserId("hglee5");
        pDTO.setUserName("이협건교수");
        pDTO.setEmail("hglee5@kopo.ac.kr");
        pDTO.setAddr("부산");

        rList = studentService.updateStudent(pDTO);

        log.info("===== 실습 #2 : 학생 수정 후 목록 =====");
        printStudentList(rList);

        // ===== 실습 #3 : 학생 삭제 =====
        pDTO = new StudentDTO();
        pDTO.setUserId("hglee5");

        rList = studentService.deleteStudent(pDTO);

        log.info("===== 실습 #3 : 학생 삭제 후 목록 =====");
        printStudentList(rList);
    }

    // 학생 목록을 보기 좋게 출력하는 공통 메서드
    private void printStudentList(List<StudentDTO> rList) {
        rList.forEach(dto -> {
            log.info("DB에 저장된 아이디 : " + dto.getUserId());
            log.info("DB에 저장된 이름 : " + dto.getUserName());
            log.info("DB에 저장된 이메일 : " + dto.getEmail());
            log.info("DB에 저장된 주소 : " + dto.getAddr());
            log.info("--------------------");
        });
    }
}