import java.util.List;
import java.util.Scanner;

import controller.OrderProgramController;
import dto.OrderDTO;
import dto.UserDTO;

public class App {

    // 사용자 입력을 위한 도구
    private static Scanner scanner = new Scanner(System.in, "cp949");

    // Controller 레이어
    private static OrderProgramController controller = new OrderProgramController();

    private static boolean flag = false;

    // 로그인 정보를 저장한 변수
    private static UserDTO userInfo = null; // 로그인 하면 정보를 추가, 로그아웃 하면 null로 변경.

    public static void main(String[] args) throws Exception {
        System.out.println("[고객 주문 관리 프로그램]");
        menu();
    }

    public static void menu() { // 메인 메뉴(View)
        while (true) {
            System.out.println("1. 회원 관리");
            System.out.println("2. 주문 관리");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택 : ");

            char choice = scanner.nextLine().charAt(0);
            System.out.println();

            switch (choice) {
                case '1':
                    // 회원 가입, 로그인 정보를 출력하는 메뉴 메서드 호출
                    userManageMenu();
                    break;
                case '2':
                    // 주문 처리(회원), 주문 처리(비회원)
                    userOrder();
                    break;
                case '0':
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return; // 프로세스 종료
                default:
                    System.out.println("다시 입력하세연");
                    break;
            }
        }
    }

    public static void userManageMenu() {
        while (true) {
            System.out.println("1) 회원 가입");
            System.out.println("2) 로그인");
            System.out.println("0) 메인으로 이동");
            System.out.print("메뉴 선택 : ");

            char choice = scanner.nextLine().charAt(0);
            System.out.println();

            switch (choice) {
                case '1':
                    // 회원 가입 정보 처리 메서드 호출
                    System.out.println("회원 가입 정보 처리 메서드");
                    joinUser();
                    break;
                case '2':
                    // 로그인 처리 메서드 호출
                    System.out.println("로그인 처리 메서드");
                    login();
                    break;
                case '0':
                    System.out.println("메인으로 이동합니다.");
                    return;
                default:
                    System.out.println("다시 입력하세연");
                    break;
            }
        }
    }

    public static void joinUser() {

        while (true) {
            System.out.println("[ 회원 가입 정보 처리 ]");
            System.out.print("사용자 아이디 : ");
            String userId = scanner.next();
            System.out.print("사용자 패스워드 : ");
            String userPw = scanner.next();
            scanner.nextLine(); // 버퍼 정리
            System.out.print("사용자 이름 : ");
            String userName = scanner.nextLine();
            System.out.print("사용자 이메일 : ");
            String userEmail = scanner.next();
            scanner.nextLine(); // 버퍼 정리
            System.out.print("사용자 전화번호 : ");
            String userPhone = scanner.nextLine();
            System.out.print("나이 : ");
            int userAge = scanner.nextInt();
            scanner.nextLine(); // 버퍼 정리
            System.out.print("주소1(번지) : ");
            String userAddress1 = scanner.nextLine();
            System.out.print("주소2(상세주소) : ");
            String userAddress2 = scanner.nextLine();
            System.out.print("[ 입력한 정보를 확인 ]");
            System.out.println("사용자 ID : " + userId);
            System.out.println("사용자 PW : " + userPw);
            System.out.println("사용자 이름 : " + userName);
            System.out.println("사용자 이메일 : " + userEmail);
            System.out.println("사용자 전화번호 : " + userPhone);
            System.out.println("나이 : " + userAge);
            System.out.println("주소1 : " + userAddress1);
            System.out.println("주소2 : " + userAddress2);

            while (true) {
                System.out.print("입력한 정보로 회원 가입을 하시겠습니까?(Y/N) ");
                char done = scanner.nextLine().toUpperCase().charAt(0);

                if (done == 'Y') {
                    // 회원 가입 처리
                    boolean status = controller.join(userId, userPw, userName, userEmail,
                            userPhone, userAge, userAddress1, userAddress2);

                    // 회원 가입 메뉴 나가고, 실패하면 다시 while문으로
                    if (status)
                        return;
                    else {
                        System.out.println("회원 가입 실패");
                        break;
                    }

                } else if (done == 'N') {
                    break;
                }
            }
        }
    }

    public static void login() {
        while (true) {
            if(flag == true) {  // 로그인 후 나왔을 때 메뉴로 가기 위한 처리
                flag = !flag;
                return;
            }

            System.out.println("[ 로그인 ]");
            System.out.print("사용자 아이디 : ");
            String userId = scanner.next();
            System.out.print("사용자 패스워드 : ");
            String userPw = scanner.next();
            scanner.nextLine();

            while (true) {
                if(flag == true) {
                    return;
                }

                System.out.print("로그인 하시겠습니까?(Y/N) ");
                char done = scanner.nextLine().toUpperCase().charAt(0);

                if (done == 'Y') {
                    // 로그인
                    // 성공 시 : 정보 확인, 수정, 탈퇴 메뉴를 연결
                    // 실패 시 : 아이디 또는 패스워드가 다릅니다.
                    // 다시 입력 반복 (계속 여부 확인)

                    userInfo = controller.login(userId, userPw);

                    if (!userInfo.getUserId().isEmpty()) { // 아이디 패스워드 일치 여부 확인
                        userManage();
                    } else {
                        System.out.println("아이디 또는 패스워드가 다릅니다.");
                    }

                    break;
                } else if (done == 'N') {
                    return;
                }
            }
        }
    }

    public static void userManage() {
        while (true) {

            // controller를 통해서 정보를 입력

            System.out.println("1) 로그인 정보 확인");
            System.out.println("2) 로그인 정보 수정");
            System.out.println("3) 회원 탈퇴");
            System.out.println("0) 이전 메뉴로 이동");
            System.out.print("메뉴 선택 : ");
            char choice = scanner.nextLine().charAt(0);

            switch (choice) {
                case '1':
                    // 회원 정보 출력
                    System.out.print("[ 회원 정보 출력 ]");
                    System.out.println("사용자 ID : " + userInfo.getUserId());
                    System.out.println("사용자 PW : " + userInfo.getUserPw());
                    System.out.println("사용자 이름 : " + userInfo.getUserName());
                    System.out.println("사용자 이메일 : " + userInfo.getUserEmail());
                    System.out.println("사용자 전화번호 : " + userInfo.getPhone1() + "-" + userInfo.getPhone2());
                    System.out.println("나이 : " + userInfo.getAge());
                    System.out.println("주소1 : " + userInfo.getAddress1());
                    System.out.println("주소2 : " + userInfo.getAddress2());
                    break;
                case '2':
                    // 회원 정보 출력 후 수정
                    System.out.println("[ 회원 정보 수정 ]");
                    System.out.println("사용자 ID() : " + userInfo.getUserId());
                    // 패스워드 수정은 별도의 로직으로 구성해야 합니다.
                    System.out.printf("사용자 PW(%s) : ", userInfo.getUserPw());
                    String userPw = scanner.next();
                    scanner.nextLine(); // 버퍼
                    System.out.printf("사용자 이름(%s) : ", userInfo.getUserName());
                    String userName = scanner.nextLine();
                    System.out.printf("사용자 이메일(%s) : ", userInfo.getUserEmail());
                    String userEmail = scanner.nextLine();
                    System.out.printf("사용자 전화번호(%s) : ", (userInfo.getPhone1() + "-" + userInfo.getPhone2()));
                    String userPhone = scanner.nextLine();
                    System.out.printf("나이(%d) : ", userInfo.getAge());
                    int userAge = scanner.nextInt();
                    scanner.nextLine(); // 버퍼
                    System.out.printf("주소1(%s) : ", userInfo.getAddress1());
                    String userAddress1 = scanner.nextLine();
                    System.out.printf("주소2(%s) : ", userInfo.getAddress2());
                    String userAddress2 = scanner.nextLine();
                    boolean status = controller.userModify(userInfo.getId(), userInfo.getUserId(), userPw, userName,
                            userEmail,
                            userPhone, userAge, userAddress1, userAddress2);

                    if (status) {
                        System.out.println("회원 정보가 추가 되었습니다.");
                        userInfo = controller.userInfo(userEmail); // 회원 정보 갱신
                    } else
                        System.out.println("회원 정보 수정 실패");
                    break;
                case '3':
                    // 회원 정보 출력 후 삭제
                    System.out.println("[ 회원 정보 삭제 및 확인 ]");
                    System.out.println("사용자 ID : " + userInfo.getUserId());
                    System.out.print("탈퇴하시겠습니까?(Y/N) ");

                    char done = scanner.nextLine().toUpperCase().charAt(0);
                    if (done == 'Y') {
                        System.out.print("사용자 PW : ");
                        String pw = scanner.next();
                        // 회원 정보 넣어서 보낼 pw
                        // 회원 정보 삭제를 위한 확인 처리할 패스워드. 변경해서
                        userInfo.setUserPw(pw);
                        status = controller.removeUser(userInfo);
                        if (status) { // 회원 탈퇴
                            System.out.println("회원 탈퇴 완료");
                            // 1. userInfo를 정리 => null
                            userInfo = null;
                            // 2. 이전 메뉴로 이동
                            return;
                        } else { // 회원 탈퇴 실패
                            System.out.println("회원 탈퇴 실패");
                        }

                        return;
                    } else {
                        // 취소
                    }
                    break;
                case '0':
                    flag = true;
                    return;
                default:
                    break;
            }
        }
    }

    public static void userOrder() {
        while (true) {
            System.out.println("1) 주문 처리(회원)");
            System.out.println("2) 주문 처리(비회원-x)");
            System.out.println("0) 이전 메뉴");
            System.out.print("메뉴 선택 : ");
            char choice = scanner.nextLine().charAt(0);

            switch (choice) {
                case '1':
                    // 주문 생성, 조회, 수정, 삭제
                    System.out.println("[ 회원 주문 작업 ]");
                    orderManage();
                    break;
                case '2':
                    System.out.println("작업x");
                    break;
                case '0':
                    System.out.println("이전 메뉴로 이동합니다.");
                    return;
                default:
                    System.out.println("다시 골라");
                    break;
            }
        }
    }

    public static void orderManage() {
        if(userInfo == null) {
            System.out.println("로그인한 사용자만 이용 가능");
            return;
        }

        boolean status = false;
        List<OrderDTO> list = null;

        // 주문 생성, 조회, 수정, 삭제(회원인 경우)
        while (true) {
            System.out.println("1) 주문 생성");
            System.out.println("2) 주문 조회");
            System.out.println("3) 주문 수정/삭제");
            System.out.println("0) 이전 메뉴");
            System.out.print("메뉴 입력 : ");
            char choice = scanner.nextLine().charAt(0);

            switch (choice) {
                case '1':
                    System.out.println("[ 주문 생성 ]");
                    System.out.print("메뉴 입력(list) : ");
                    String orderList = scanner.nextLine();
                    System.out.print("가격 : ");
                    int price = scanner.nextInt();
                    scanner.nextLine(); // 버퍼 정리
                    // controller에서 메뉴 처리..
                    status = controller.createOrder(userInfo, orderList, price);

                    if (status)
                        System.out.println("주문 생성 성공");
                    else
                        System.out.println("주문 생성 실패");

                    break;
                case '2':
                    // 주문 정보 읽어오기
                    // 반복문으로 처리. (stream 또는 for)
                    list = controller.getOrders(userInfo);
                    System.out.println("[ 주문 내역 읽어오기 ]");
                    list.stream().forEach(System.out::println);
                    break;
                case '3':
                    // 주문 리스트를 확인. 인덱스 번호 입력 받아서
                    list = controller.getOrders(userInfo);

                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + i) + " : " + list.get(i));
                    }

                    // 주문 삭제 처리
                    System.out.print("삭제할 번호(index) : ");
                    int idx = scanner.nextInt();
                    scanner.nextLine(); // 버퍼 정리

                    // 삭제 작업 진행
                    status = controller.removeOrder(userInfo, list.get(idx));

                    if (status)
                        System.out.println("삭제 성공");
                    else
                        System.out.println("삭제 실패");

                    break;
                case '0':
                    System.out.println("이전 메뉴로 이동합니다.");
                    return;
                default:
                    System.out.println("다시 골라");
                    break;
            }
        }
    }
}
