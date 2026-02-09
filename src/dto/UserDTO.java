package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = { "userPw", "regDate", "modifyDate" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String phone1;
    private String phone2;
    private int age;
    private String address1;
    private String address2;

    // 다른 타입의 데이터로 바꿔질 수 있다는 것을 보여주기 위해
    // Timestamp -> String
    private String regDate;
    private String modifyDate;
}
