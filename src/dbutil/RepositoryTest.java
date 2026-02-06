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
        UserVO afterData = UserVO.builder()
        .person_userId("test123").person_userPw("test123")
        .person_userName("test123").person_userEmail("test123@test.com")
        .build();

        if(repository.userMod(testData, afterData) != 0) {
            System.out.println("수정 성공");
        } else {
            System.out.println("수정 실패");
        }

        // 조건 조회
        list = repository.userSearch("test123", "test123");
        for(UserVO vo : list)
            System.out.println(vo);

        list = repository.userSearch("test123@test.com");
        for(UserVO vo : list)
            System.out.println(vo);


        // 삭제
        if(repository.userDel(afterData) != 0) {
            System.out.println("삭제 성공");
        } else {
            System.out.println("삭제 실패");
        }

    }
}
