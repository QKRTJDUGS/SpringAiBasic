package kopo.poly.service.impl;

import kopo.poly.service.INlpService;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NlpServiceImpl implements INlpService {

    @Override
    public List<String> getNouns(String text) throws Exception {

        log.info("{}.getNouns Start!", this.getClass().getName());

        // Komoran 객체 생성 (LIGHT = 가벼운 모델)
        Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);

        // 문장을 형태소 분석
        KomoranResult result = komoran.analyze(text);

        // 분석 결과에서 명사만 추출
        List<String> nouns = result.getNouns();

        log.info("{}.getNouns End!", this.getClass().getName());

        return nouns;
    }
}