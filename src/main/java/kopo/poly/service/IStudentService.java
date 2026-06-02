package kopo.poly.service;

import kopo.poly.dto.StudentDTO;

import java.util.List;

public interface IStudentService {

    // 학생 등록 후, 등록된 전체 학생 목록을 돌려준다.
    List<StudentDTO> insertStudent(StudentDTO pDTO) throws Exception;

    // 학생 수정 후, 수정된 전체 학생 목록을 돌려준다.
    List<StudentDTO> updateStudent(StudentDTO pDTO) throws Exception;

    // 학생 삭제 후, 삭제된 전체 학생 목록을 돌려준다.
    List<StudentDTO> deleteStudent(StudentDTO pDTO) throws Exception;

}
