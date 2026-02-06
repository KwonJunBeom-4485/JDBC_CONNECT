import domain.PersonLom;

public class App {
    public static void main(String[] args) throws Exception {

        // lombok 테스트
        PersonLom lom = new PersonLom();
        // @AllArgsConstructor
        PersonLom lom2 = new PersonLom(0, "1", "1", "1", "1", "1", "1", (byte) 0, "1", "1", null, null);

        // @Setter
        lom.setPerson_address1("주소1");
        
        // @Getter
        lom2.getPerson_address1();


        // @Builder 사용 시
        PersonLom lomboktest = new PersonLom().builder()
        .person_address1("주소1").build();


        System.out.println("Hello, World!");
    }
}
