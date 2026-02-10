package dbutil;

import repository.UsersDAOImpl;

import java.util.ArrayList;
import java.util.List;

import domain.users.UserVO;
import repository.Users;

public class RepositoryTest {
    private static Users repository = new UsersDAOImpl();

    public static void main(String[] args) {
        // test data
        List<UserVO> list = new ArrayList<>();

        // 삽입
        UserVO testData = UserVO.builder()
        .person_userId("test111").person_userPw("test11")
        .person_userName("test111").person_userEmail("test111@test.com")
        .person_age(32).person_phone1("02").person_phone2("8887777")
        .person_address1("testuser의 주소1").person_address2("testuser의 주소2")
        .build();

        if(repository.userAdd(testData) != 0) {
            System.out.println("삽입 성공");
        } else {
            System.out.println("삽입 실패");
        }

        // 전체 조회
        list = repository.userAll();
        for(UserVO vo : list)
            System.out.println(vo); // person_userPw를 빼고 모두 출력 (보안 상 UserVO에서 미리 걸렀음)

        
        // 수정
        // UserVO afterData = UserVO.builder()
        // .person_userId("test123").person_userPw("test123")
        // .person_userName("test123").person_userEmail("test123@test.com")
        // .person_address2("수정된 주소")
        // .build();


        testData.setPerson_userId("test123");
        testData.setPerson_userPw("test123");
        testData.setPerson_userName("test123");
        testData.setPerson_userEmail("test123@test.com");
        testData.setPerson_address2("수정된 주소");

        // 수정하는 데이터에 따라 매번 객체를 builder 하지 않고, before에 testData를 저장하고
        // setter로 값을 바꾼 testData를 넣어 userMod(before, testData) 이렇게 해도 좋다.

        if(repository.userMod(testData) != 0) {
            System.out.println("수정 성공");
        } else {
            System.out.println("수정 실패");
        }

        // 조건 조회

        // list = repository.userSearch("test123@test.com");
        // for(UserVO vo : list)
        //     System.out.println(vo);

        // 이렇게도 출력 가능
        repository.userSearch("test123@test.com").stream()
        .forEach(System.out::println);

        // 삭제
        if(repository.userDel(testData) != 0) {
            System.out.println("삭제 성공");
        } else {
            System.out.println("삭제 실패");
        }

    }
}
