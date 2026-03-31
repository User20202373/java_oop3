package http_client;

import com.google.gson.Gson;
import http_client.dtos.Post;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpPostParsingEx {

    public static void main(String[] args) {

        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();

        try {
            // 1.
            // 자바 객체를 ---> 문자열 변환(JSON 문자열 ) --> 서버 측으로 데이터
            Post post = Post.builder()
                    .userId(1)
                    .title("HTTP 통신 요청")
                    .body("METHOD 는  POST 요청 데이터를 함께 보내야 함")
                    .build();

            String postString = gson.toJson(post); //서버측으로 보내니까 toJson

            // 2. 요청 HTTP 메세지 생성
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                    .header("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(postString))
                    .build();

            //3. 실제 전송 및 응답
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.toString());
            System.out.println(response.headers());
            System.out.println(response.body());

            // ??? 파싱 --> json 문자열을 자바에서 사용할 Object 변환하는 작업
            Post createdPost = gson.fromJson(response.body(), Post.class);

            //결과 확인
            System.out.println(createdPost.getId());
            System.out.println(createdPost.getUserId());
            System.out.println(createdPost.getTitle());
            System.out.println(createdPost.getBody());

            // 새로운 리소스를 서버에 등록을 할 때. 우리는 자바 객체를 한 줄로 길게 늘려서 "문자열"이나 "바이트 배열"
            // 같은 형태로 납작하게 펴주는 과정이 필요한데 이걸 바로 직렬화 라고 합니다.
            // 서버로부터 JSON 응답을 받아서 다시 자바객체로 펴주는 과정을 역직렬화라고도 부릅니다.

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }//end of main
}//end of class
