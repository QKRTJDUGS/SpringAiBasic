package kopo.poly.mapper;

import kopo.poly.dto.StudentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStudentMapper {

    // 학생 등록 (INSERT)
    int insertStudent(StudentDTO pDTO) throws Exception;

    // 전체 학생 목록 조회 (SELECT)
    List<StudentDTO> getStudentList() throws Exception;

    // 특정 학생 1명 조회 (중복 검사용)
    StudentDTO getStudent(StudentDTO pDTO) throws Exception;

    // 학생 수정 (UPDATE)
    int updateStudent(StudentDTO pDTO) throws Exception;

    // 학생 삭제 (DELETE)
    int deleteStudent(StudentDTO pDTO) throws Exception;

}
