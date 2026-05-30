package kopo.poly;

import kopo.poly.dto.OcrDTO;
import kopo.poly.service.INlpService;
import kopo.poly.service.IOcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        // 2~3단계 : 단어별 등장 횟수 세기 (Map에 "단어 → 횟수"로 저장)
        Map<String, Integer> wordCount = new HashMap<>();
        for (String noun : nouns) {
            wordCount.put(noun, wordCount.getOrDefault(noun, 0) + 1);
        }

        // 4단계 : 횟수 많은 순으로 정렬
        Map<String, Integer> sorted = new LinkedHashMap<>();
        wordCount.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .forEach(e -> sorted.put(e.getKey(), e.getValue()));

        // 결과 출력 (상위 10개)
        log.info("===== 가장 많이 쓰인 단어 TOP 10 =====");
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            log.info("{}위 : {} ({}번)", rank, entry.getKey(), entry.getValue());
            rank++;
            if (rank > 10) break;
        }
    }
}