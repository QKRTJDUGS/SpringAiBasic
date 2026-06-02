package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {

    private String userId;   // 학생 아이디 (USER_ID)
    private String userName; // 학생 이름 (USER_NAME)
    private String email;    // 이메일 (EMAIL)
    private String addr;     // 주소 (ADDR)

}
