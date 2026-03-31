package http.toJson;

public class UserEx1 {
    public static void main(String[] args) {
        //User DTO 클래스를 내부 클래스에 필드를 만들었을 경우 사용법
        User user = new User();
        user.setId(100);
        user.setName("홍길동");
        user.setEmail("a@naver.com");

        // User 안에 있는 Address 객체를 생성하는 방법
        User.Address address = user.new Address();
        address.setCity("부산");
        address.setStreet("중앙대로");
        user.setAddress(address);

        User.Address.Geo geo = user.getAddress().new Geo();
        address.setGeo(geo);
        geo.setLat("55.6");
        geo.setLng("112.6");


    }
}
