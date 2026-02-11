package service.usermanage;

// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import domain.users.UserVO;
import dto.UserDTO;
import repository.UserDAOImplOracle;
import repository.Users;
import repository.UsersDAOImpl;
import repository.UsersDAOImplMariadb;

public class UserManageImpl implements UserManage {

    // public static void main(String[] args) {
    // // 1. 서비스 객체 생성
    // UserManage userManage = new UserManageImpl();

    // // 2. searchAll 호출
    // List<UserDTO> userList = userManage.searchAll();
    // for(UserDTO dto : userList) {
    // System.out.println(dto);
    // }
    // }

    // DB 작업을 할 수 있는 객체를 호출 작업 진행...
    // 인터페이스를 통한 객체 호출...
    // Users userRepository = new UsersDAOImpl();

    // 오라클 버전
    // Users userRepository = new UserDAOImplOracle();

    Users userRepository = new UsersDAOImplMariadb();

    @Override
    public List<UserDTO> searchAll() {

        List<UserDTO> list = new ArrayList<>();
        List<UserVO> userVOList = userRepository.userAll();
        for (UserVO vo : userVOList) {
            // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            UserDTO dto = UserDTO.toUserDTO(vo);

            list.add(dto);
        }

        return list;
    }

    @Override
    public UserDTO searchOne(String userEmail) {
        UserVO vo = userRepository.userSearch(userEmail).get();

        // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        UserDTO dto = UserDTO.toUserDTO(vo);

        return dto;
    }

    @Override
    public boolean userDelete(UserDTO userDTO) {
        // UserDTO -> UserVO
        UserVO userVO = UserDTO.toUserVO(userDTO);

        if (userRepository.userDel(userVO) != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean userModify(UserDTO userDTO) {
        UserVO userVO = UserDTO.toUserVO(userDTO);

        if (userRepository.userMod(userVO) != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean userRegister(UserDTO userDTO) {
        UserVO userVO = UserDTO.toUserVO(userDTO);

        if (userRepository.userAdd(userVO) != 0)
            return true;
        else
            return false;

    }

    @Override
    public UserDTO login(String userId, String userPw) {
        UserDTO userDTO = UserDTO.toUserDTO(
                userRepository.login(userId, userPw).get());
        return userDTO;
    }

}
