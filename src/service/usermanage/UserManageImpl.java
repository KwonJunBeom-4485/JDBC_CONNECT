package service.usermanage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import domain.users.UserVO;
import dto.UserDTO;
import repository.Users;
import repository.UsersDAOImpl;

public class UserManageImpl implements UserManage {

    // DB 작업을 할 수 있는 객체를 호출 작업 진행...
    // 인터페이스를 통한 객체 호출...
    Users userRepository = new UsersDAOImpl();

    @Override
    public List<UserDTO> searchAll() {

        List<UserDTO> list = new ArrayList<>();
        List<UserVO> userVOList = userRepository.userAll();
        for(UserVO vo : userVOList) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            UserDTO dto = UserDTO.builder()
            .id(vo.getPerson_id())
            .userId(vo.getPerson_userId())
            .userPw(vo.getPerson_userPw())
            .userName(vo.getPerson_userName())
            .userEmail(vo.getPerson_userEmail())
            .phone1(vo.getPerson_phone1())
            .phone2(vo.getPerson_phone2())
            .age(vo.getPerson_age())
            .address1(vo.getPerson_address1())
            .address2(vo.getPerson_address2())
            .regDate(sf.format(vo.getRegDate()))
            .modifyDate(sf.format(vo.getModifyDate()))
            .build();

            list.add(dto);
        }

        return list;
    }

    @Override
    public UserDTO searchOne(String userEmail) {
        UserVO vo = userRepository.userSearch(userEmail).get();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        UserDTO dto = UserDTO.builder().id(vo.getPerson_id())
        .userPw(vo.getPerson_userPw())
        .userName(vo.getPerson_userName())
        .userEmail(vo.getPerson_userEmail())
        .phone1(vo.getPerson_phone1())
        .phone2(vo.getPerson_phone2())
        .age(vo.getPerson_age())
        .address1(vo.getPerson_address1())
        .address2(vo.getPerson_address2())
        .regDate(sf.format(vo.getRegDate()))
        .modifyDate(sf.format(vo.getModifyDate()))
        .build();

        return dto;
    }

    @Override
    public boolean userDelete(UserDTO userDTO) {
        // UserDTO -> UserVO
        UserVO userVO = UserVO.builder().person_id(userDTO.getId())
        .person_userId(userDTO.getUserId())
        .person_userPw(userDTO.getUserPw())
        .person_userName(userDTO.getUserName())
        .person_userEmail(userDTO.getUserEmail())
        .person_phone1(userDTO.getPhone1())
        .person_phone2(userDTO.getPhone2())
        .person_age((byte)userDTO.getAge())
        .person_address1(userDTO.getAddress1())
        .person_address2(userDTO.getAddress2())
        .build();

        if(userRepository.userDel(userVO) != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean userModify(UserDTO userDTO) {
        UserVO userVO = UserVO.builder().person_id(userDTO.getId())
                .person_userId(userDTO.getUserId())
                .person_userPw(userDTO.getUserPw())
                .person_userName(userDTO.getUserName())
                .person_userEmail(userDTO.getUserEmail())
                .person_phone1(userDTO.getPhone1())
                .person_phone2(userDTO.getPhone2())
                .person_age((byte) (userDTO.getAge())) // byte로 캐스팅 문제 확인.
                .person_address1(userDTO.getAddress1())
                .person_address2(userDTO.getAddress2())
                .build();
        if (userRepository.userMod(userVO) != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean userRegister(UserDTO userDTO) {
        UserVO userVO = UserVO.builder()
                .person_userId(userDTO.getUserId())
                .person_userPw(userDTO.getUserPw())
                .person_userName(userDTO.getUserName())
                .person_userEmail(userDTO.getUserEmail())
                .person_phone1(userDTO.getPhone1())
                .person_phone2(userDTO.getPhone2())
                .person_age((byte) (userDTO.getAge())) // byte로 캐스팅 문제 확인.
                .person_address1(userDTO.getAddress1())
                .person_address2(userDTO.getAddress2())
                .build();
        if (userRepository.userAdd(userVO) != 0)
            return true;
        else
            return false;

    }

    
}
