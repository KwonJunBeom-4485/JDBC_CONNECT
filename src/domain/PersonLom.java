package domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*
Lombok에서 주로 사용하는 어노테이션...
@getter / @setter : Getter/Setter 설정
@ToString : toString 메서드 생성.
@EqualsAndHashCode : equals()와 hashCode() 메서드를 자동 구현.
@Data : getter, setter, toString, equalsAndHashCode 어노테이션 기능을 모두 포함.
@Builder : 복잡한 객체 생성을 안정화하는 Builder를 자동 생성.

@AllArgsConstructor : 멤버변수 전체를 사용하는 생성자를 만들어 줌.
@NoArgConstructor : 기본 생성자를 만들어 줌.

주의점
1. 무분별한 어노테이션의 사용으로 다른 기능과 연결되어 의도하지 않은 동작을 할 수 있음. (Data 어노테이션은 자중하는 것이 좋다)
2. @Builder만 사용하면 기본 생성자는 생성되지 않음.
    기본 생성자가 필요한 경우에는 @NoArgConstructor 사용하는 것이 일반적입니다.
    @AllArgsConstructor는 위에 @NoArgConstructor를 사용하는 경우에 같이 사용.
3. Lombok만 의존하면 문제가 발생했을 때, 대처하기 어렵습니다. Lombok을 사용하지 못하는 경우를 대비할 필요 있음.

*/

@Data
@Builder
@AllArgsConstructor
public class PersonLom {

    // 멤버 변수 선언
    private int person_id;
    private String person_userId;
    private String person_userPw;
    private String person_userName;
    private String person_userEmail;
    private String person_phone1;
    private String person_phone2;
    private byte person_age;
    private String person_address1;
    private String person_address2;
    private Timestamp regDate;
    private Timestamp modifyDate;

    public PersonLom() {

    }

}
