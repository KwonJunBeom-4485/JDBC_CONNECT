package dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import domain.users.UserVO;
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


    public static UserVO toUserVO(UserDTO userDTO) {
        return UserVO.builder()
                .person_id(userDTO.id)
                .person_userId(userDTO.userId)
                .person_userPw(userDTO.userPw)
                .person_userPw(userDTO.userPw)
                .person_userEmail(userDTO.userEmail)
                .person_phone1(userDTO.phone1)
                .person_phone2(userDTO.phone2)
                .person_age(toChangeAge(userDTO.age))
                .person_address1(userDTO.address1)
                .person_address2(userDTO.address2)
                .regDate(toDateTimestamp(userDTO.getRegDate()))
                .modifyDate(toDateTimestamp(userDTO.getModifyDate()))
                .build();
    }

    public static UserDTO toUserDTO(UserVO userVO) {
        return UserDTO.builder()
                .id(userVO.getPerson_id())
                .userId(userVO.getPerson_userId())
                .userPw(userVO.getPerson_userPw())
                .userName(userVO.getPerson_userName())
                .userEmail(userVO.getPerson_userEmail())
                .phone1(userVO.getPerson_phone1())
                .phone2(userVO.getPerson_phone2())
                .age(toChangeAge(userVO.getPerson_age()))
                .address1(userVO.getPerson_address1())
                .address2(userVO.getPerson_address2())
                .regDate(toTimestampDateString(userVO.getRegDate()))
                .modifyDate(toTimestampDateString(userVO.getModifyDate()))
                .build();
    }

    private static byte toChangeAge(int age) {
        if (age >= 0 && age <= 127)
            return (byte) age;
        else
            return 0;
    }

    private static Timestamp toDateTimestamp(String dateString) {
        if(dateString == null)
            return new Timestamp(0);    // 1970.01.01 00:00:00 -> 서울 기준 09시

        String[] arrDayTime = dateString.split(" ");
        String[] day = arrDayTime[0].split("-");
        String[] time = arrDayTime[1].split(":");
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(day[0]), Integer.parseInt(day[1]), Integer.parseInt(day[2]),
                Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
        return new Timestamp(cal.getTimeInMillis());
    }

    private static String toTimestampDateString(Timestamp time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(time);
    }
}


