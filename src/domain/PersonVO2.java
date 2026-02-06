package domain;

import java.sql.Timestamp;

public class PersonVO2 {

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

    // 생성자
    public PersonVO2() {
    }

    public PersonVO2(String person_userId, String person_userPw, String person_userName,
            String person_userEmail, String person_phone1, String person_phone2, byte person_age,
            String person_address1,
            String person_address2) {
        this.person_userId = person_userId;
        this.person_userPw = person_userPw;
        this.person_userName = person_userName;
        this.person_userEmail = person_userEmail;
        this.person_phone1 = person_phone1;
        this.person_phone2 = person_phone2;
        this.person_age = person_age;
        this.person_address1 = person_address1;
        this.person_address2 = person_address2;
    }

    // Builder를 사용자는 생성자 구성.
    public PersonVO2(Builder builder) {
        this.person_id = builder.person_id;
        this.person_userId = builder.person_userId;
        this.person_userPw = builder.person_userPw;
        this.person_userName = builder.person_userName;
        this.person_userEmail = builder.person_userEmail;
        this.person_phone1 = builder.person_phone1;
        this.person_phone2 = builder.person_phone2;
        this.person_age = builder.person_age;
        this.person_address1 = builder.person_address1;
        this.person_address2 = builder.person_address2;
        this.regDate = builder.regDate;
        this.modifyDate = builder.modifyDate;
    }

    // Builder를 사용하는 생성자 구성. (static 내부 클래스)
    // 빌더의 역할은 값을 받아서 PersonVO2 객체 반환
    public static class Builder {
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

        // 기본 생성자
        public Builder() {
            
        }

        public Builder id(int id) {
            this.person_id = id; // 생성된 객체 id값을 매개변수 id 대입
            return this; // 객체를 넘기겠다
        }

        public Builder userId(String userId) {
            this.person_userId = userId; // 생성된 객체 id값을 매개변수 id 대입
            return this; // 객체를 넘기겠다
        }

        public Builder userPw(String userPw) {
            this.person_userPw = userPw; // 생성된 객체 id값을 매개변수 id 대입
            return this; // 객체를 넘기겠다
        }

        public Builder userName(String userName) {
            this.person_userName = userName; // 생성된 객체 id값을 매개변수 id 대입
            return this; // 객체를 넘기겠다
        }

        public Builder userEmail(String userEmail) {
            this.person_userEmail = userEmail; // 생성된 객체 id값을 매개변수 id 대입
            return this; // 객체를 넘기겠다
        }

        public Builder phone1(String phone1) {
            this.person_phone1 = phone1; // 생성된 객체 id값을 매개변수 id 대입
            return this; // 객체를 넘기겠다
        }

        public Builder phone2(String phone2) {
            this.person_phone2 = phone2; // 생성된 객체 id값을 매개변수 id 대입
            return this; // 객체를 넘기겠다
        }

        public Builder age(int age) {
            this.person_age = (byte) age; // 생성된 객체 id값을 매개변수 id 대입
            return this; // 객체를 넘기겠다
        }

        public Builder address1(String address1) {
            this.person_address1 = address1; // 생성된 객체 id값을 매개변수 id 대입
            return this; // 객체를 넘기겠다
        }

        public Builder address2(String address2) {
            this.person_address2 = address2; // 생성된 객체 id값을 매개변수 id 대입
            return this; // 객체를 넘기겠다
        }

        public Builder regDate(Timestamp regDate) {
            this.regDate = regDate; // 생성된 객체 id값을 매개변수 id 대입
            return this; // 객체를 넘기겠다
        }

        public Builder modifyDate(Timestamp modifyDate) {
            this.modifyDate = modifyDate; // 생성된 객체 id값을 매개변수 id 대입
            return this; // 객체를 넘기겠다
        }

        // 메서드 : build()
        public PersonVO2 build() {
            return new PersonVO2(this); // Builder 내부 클래스를 받아서 처리하는 생성자
        }

    }

    // 메서드(Getter, Setter)
    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getPerson_age() {
        return person_age;
    }

    public void setPerson_age(byte person_age) {
        this.person_age = person_age;
    }

    public String getPerson_userId() {
        return person_userId;
    }

    public void setPerson_userId(String person_userId) {
        this.person_userId = person_userId;
    }

    public String getPerson_userPw() {
        return person_userPw;
    }

    public void setPerson_userPw(String person_userPw) {
        this.person_userPw = person_userPw;
    }

    public String getPerson_userName() {
        return person_userName;
    }

    public void setPerson_userName(String person_userName) {
        this.person_userName = person_userName;
    }

    public String getPerson_userEmail() {
        return person_userEmail;
    }

    public void setPerson_userEmail(String person_userEmail) {
        this.person_userEmail = person_userEmail;
    }

    public String getPerson_phone1() {
        return person_phone1;
    }

    public void setPerson_phone1(String person_phone1) {
        this.person_phone1 = person_phone1;
    }

    public String getPerson_phone2() {
        return person_phone2;
    }

    public void setPerson_phone2(String person_phone2) {
        this.person_phone2 = person_phone2;
    }

    public String getPerson_address1() {
        return person_address1;
    }

    public void setPerson_address1(String person_address1) {
        this.person_address1 = person_address1;
    }

    public String getPerson_address2() {
        return person_address2;
    }

    public void setPerson_address2(String person_address2) {
        this.person_address2 = person_address2;
    }

    @Override
    public String toString() {
        return "PersonVO [number:" + person_id + ", 나이: " + person_age + ", 아이디: " + person_userId
                + ", 패스워드: " + person_userPw + ", 이름: " + person_userName + ", 이메일: "
                + person_userEmail + ", 폰1: " + person_phone1 + ", 폰2: " + person_phone2
                + ", 주소1: " + person_address1 + ", 주소2: " + person_address2 + ", regDate="
                + regDate + ", modifyDate=" + modifyDate + "]";
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

}
