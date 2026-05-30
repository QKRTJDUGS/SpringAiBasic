package kopo.poly.service.impl;

import kopo.poly.dto.OcrDTO;
import kopo.poly.service.IOcrService;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class OcrServiceImpl implements IOcrService {

    @Override
    public OcrDTO getOcr(String imagePath) throws Exception {

        log.info("{}.getOcr Start!", this.getClass().getName());

        OcrDTO rDTO = new OcrDTO(); // 결과를 담을 상자

        Tesseract tesseract = new Tesseract(); // Tesseract 객체 생성

        tesseract.setDatapath(MODEL_PATH); // 학습모델 폴더 경로
        tesseract.setLanguage(LANGUAGE);   // 인식 언어 (kor)

        File imageFile = new File(imagePath); // 읽을 이미지 파일

        String result = tesseract.doOCR(imageFile); // ★문자 인식 실행

        log.info("인식 결과 : {}", result);

        rDTO.setResult(result); // 결과를 DTO에 저장

        log.info("{}.getOcr End!", this.getClass().getName());

        return rDTO;
    }
}