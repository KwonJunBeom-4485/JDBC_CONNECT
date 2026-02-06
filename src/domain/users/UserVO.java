package domain.users;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "person_userPw")    // person_userPw 빼고 toString 메서드 생성
@Builder                                // Builder 클래스 사용
@NoArgsConstructor                      // 기본 생성자
@AllArgsConstructor                     // 모든 멤버 변수 사용 생성자
public class UserVO {
    // 멤버 변수 선언
    private long person_id;
    private String person_userId;
    private String person_userPw;
    private String person_userName;
    private String person_userEmail;
    private String person_phone1;
    private String person_phone2;
    private int person_age;
    private String person_address1;
    private String person_address2;
    private Timestamp regDate;
    private Timestamp modifyDate;
}
