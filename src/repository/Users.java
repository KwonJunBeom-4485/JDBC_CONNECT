package repository;

import java.util.List;
import java.util.Optional;

import domain.users.UserVO;

public interface Users {
    // 레코드 추가
    int userAdd(UserVO user);

    // 레코드 수정
    int userMod(UserVO user);

    // 레코드 삭제
    int userDel(UserVO user);

    // 레코드 조회
    // 1. 전체 조회    
    List<UserVO> userAll();
    
    // 2. 조건 조회(userid(unique), name), email(unique 처리 안해도 unique)
    List<UserVO> userSearch(String userId, String userName);
    Optional<UserVO> userSearch(String userEmail);
}
