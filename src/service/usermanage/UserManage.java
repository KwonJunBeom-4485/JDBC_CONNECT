package service.usermanage;

import java.util.List;

import dto.UserDTO;

public interface UserManage {

    // 1. 사용자 등록
    boolean userRegister(UserDTO userDTO);

    // 2. 사용자 수정
    boolean userModify(UserDTO userDTO);

    // 3. 사용자 검색(특정 사용자 검색, 전체 사용자 검색)
    // 특정 사용자(userId는 유니크라 한개만 결과 출력 = UserDTO 타입으로 반환해도 ok)
    UserDTO searchOne(String userEmail);
    // 전체 사용자
    List<UserDTO> searchAll();

    // 4. 사용자 삭제
    boolean userDelete(UserDTO userDTO);

}
