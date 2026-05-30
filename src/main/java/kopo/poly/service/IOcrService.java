package kopo.poly.service;

import kopo.poly.dto.OcrDTO;

public interface IOcrService {

    // 학습 모델이 저장된 폴더 경로
    String MODEL_PATH = "C:\\model\\tessdata";

    // 인식할 언어 (kor = 한국어)
    String LANGUAGE = "kor";

    // 이미지 파일의 문자를 인식하는 함수
    OcrDTO getOcr(String imagePath) throws Exception;

}