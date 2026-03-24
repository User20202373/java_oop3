package http.json;

public class NotJsonEx2 {
    public static void main(String[] args) {

        String json = "{\"userId\": 1,\"id\": 1,\"title\": \"quidem molestiae enim\"}";
        //JSON   형식에 문자열을 파싱해서 Album 객체로 반환해보자

        String step1 = json.replace("{", "").replace("}", "");

        String[] parts = step1.split(",");
        System.out.println("size : " + parts.length);
        System.out.println(parts[0]);
        System.out.println(parts[1]);

        //userId 추출
        String userIdValue = (parts[0].split(":"))[1];
        System.out.println("userId 추출");
        System.out.println(userIdValue);

        //id 추출
        String idValue = parts[1].split(":")[1];
        System.out.println("Id 추출");
        System.out.println(idValue);

        //title 추출
        String titleValue = parts[2].split(":")[1];
        System.out.println("title 추출");
        System.out.println(titleValue);

    }//end of main
}//end of class

class Album {
    int userId;
    int id;
    String title;

    public Album(int userId, int id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Album{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}