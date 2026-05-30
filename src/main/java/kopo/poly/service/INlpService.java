package kopo.poly.service;

import java.util.List;

public interface INlpService {

    // 문장에서 명사만 뽑아내는 함수
    List<String> getNouns(String text) throws Exception;

}