package kopo.poly.service.impl;

import kopo.poly.dto.StudentDTO;
import kopo.poly.mapper.IStudentMapper;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements IStudentService {

    private final IStudentMapper studentMapper;

    /**
     * 학생 정보를 DB에 등록한 후, 등록된 학생 목록을 조회하여 반환하는 메서드
     *
     * @param pDTO 등록할 학생 정보를 담고 있는 DTO 객체
     * @return 전체 학생 목록 (등록 결과 포함)
     * @throws Exception 예외 처리
     */
    @Override
    public List<StudentDTO> insertStudent(StudentDTO pDTO) throws Exception {

        // 현재 실행 중인 클래스명을 로그에 출력하기 위해 저장
        String className = this.getClass().getName();
        log.info("{}.insertStudent Start!", className);

        // 1. DB에 동일한 학생 아이디(USER_ID)가 존재하는지 조회
        // - SELECT 쿼리 실행 결과는 Optional로 감싸 null 가능성 방지
        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO)
        );

        // 2. 조회 결과가 없으면 (즉, 중복되지 않았으면) insert 쿼리 실행
        if (res.isEmpty()) {
            // DB에 동일한 아이디가 없으므로 insert 쿼리 실행
            studentMapper.insertStudent(pDTO);
            log.info("학생 등록 완료 - ID: {}", pDTO.getUserId());
        } else {
            // 이미 존재하는 경우 등록하지 않음
            log.warn("학생 등록 실패 - 이미 존재하는 ID: {}", pDTO.getUserId());
        }

        // 3. 전체 학생 목록 다시 조회
        // - getStudentList()가 null일 경우 빈 리스트 반환
        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);

        // 4. 메서드 종료 로그 출력
        log.info("{}.insertStudent End!", className);

        // 5. 전체 학생 목록 반환
        return rList;
    }

    /**
     * 학생 정보를 수정한 후, 수정된 학생 목록을 조회하여 반환하는 메서드
     *
     * @param pDTO 수정할 학생 정보를 담고 있는 DTO 객체
     * @return 전체 학생 목록 (수정 결과 포함)
     * @throws Exception 예외 처리
     */
    @Override
    public List<StudentDTO> updateStudent(StudentDTO pDTO) throws Exception {

        String className = this.getClass().getName();
        log.info("{}.updateStudent Start!", className);

        // 1. 수정하려는 학생 아이디(USER_ID)가 DB에 존재하는지 조회
        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO)
        );

        // 2. 존재하는 경우에만 update 쿼리 실행 (등록과 반대 조건)
        if (res.isPresent()) {
            studentMapper.updateStudent(pDTO);
            log.info("학생 수정 완료 - ID: {}", pDTO.getUserId());
        } else {
            log.warn("학생 수정 실패 - 존재하지 않는 ID: {}", pDTO.getUserId());
        }

        // 3. 전체 학생 목록 다시 조회
        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);

        log.info("{}.updateStudent End!", className);

        return rList;
    }

    /**
     * 학생 정보를 삭제한 후, 삭제된 학생 목록을 조회하여 반환하는 메서드
     *
     * @param pDTO 삭제할 학생 정보를 담고 있는 DTO 객체
     * @return 전체 학생 목록 (삭제 결과 포함)
     * @throws Exception 예외 처리
     */
    @Override
    public List<StudentDTO> deleteStudent(StudentDTO pDTO) throws Exception {

        String className = this.getClass().getName();
        log.info("{}.deleteStudent Start!", className);

        // 1. 삭제하려는 학생 아이디(USER_ID)가 DB에 존재하는지 조회
        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO)
        );

        // 2. 존재하는 경우에만 delete 쿼리 실행
        if (res.isPresent()) {
            studentMapper.deleteStudent(pDTO);
            log.info("학생 삭제 완료 - ID: {}", pDTO.getUserId());
        } else {
            log.warn("학생 삭제 실패 - 존재하지 않는 ID: {}", pDTO.getUserId());
        }

        // 3. 전체 학생 목록 다시 조회
        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);

        log.info("{}.deleteStudent End!", className);

        return rList;
    }
}
