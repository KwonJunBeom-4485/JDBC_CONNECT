package domain;

import java.sql.Timestamp;

// record 객체 선언 - class 대신에 record 사용. (java14 이후)
// 클래스 선언 시에 없던 "()" 사용합니다.
// getter가 기본으로 설정됨. Builder 사용 가능.
public record PersonRe(int id, String userId, String userPw, String userName, String userEmail, String phone1,
        String phone2, byte age, String address1, String address2, Timestamp regDate, Timestamp modifyDate) {

}
