package kopo.poly;

import kopo.poly.dto.OcrDTO;
import kopo.poly.service.INlpService;
import kopo.poly.service.IOcrService;
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

    public static void main(String[] args) {
        SpringApplication.run(SpringAiBasicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // ===== 실습 #1 : 이미지에서 글자 읽기 =====
        OcrDTO rDTO = ocrService.getOcr("src/main/resources/image/sample01.png");
        String text = rDTO.getResult();

        log.info("===== 인식한 글자 =====");
        log.info(text);

        // ===== 실습 #2 : 가장 많이 쓰인 단어 찾기 =====

        // 1단계 : 글에서 명사만 뽑기
        List<String> nouns = nlpService.getNouns(text);
        log.info("중복 제거 수행 전 단어 수 : {}", nouns.size());

        // 2단계 : 중복된 단어 제거 (Set은 중복을 허용하지 않음)
        Set<String> nounSet = new HashSet<>(nouns);
        log.info("중복 제거 수행 후 단어 수 : {}", nounSet.size());

        // 3단계 : 중복 제거된 단어별 등장 횟수 세기 → Map<String, Integer>
        Map<String, Integer> wordCount = new HashMap<>();
        for (String noun : nounSet) {
            wordCount.put(noun, Collections.frequency(nouns, noun));
        }

        // 4단계 : 횟수 많은 순으로 정렬
        Map<String, Integer> sorted = new LinkedHashMap<>();
        wordCount.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .forEach(e -> sorted.put(e.getKey(), e.getValue()));

        // 결과 출력 (단어 : 횟수)
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            log.info("{} : {}", entry.getKey(), entry.getValue());
        }
    }
}