package http_client;

import com.google.gson.Gson;
import http_client.dtos.Post;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpDeleteParsingEx {

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();

        try {
            // 1. 삭제 요청 생성(삭제할 대상의 ID인 /1 을 URL 끝에 명시)
            //DELETE는 보통 서버로 보낼 Body 가 없으므로 주소만 설정하면 된다.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                    .DELETE() // 바디가 없어서 따로 ()안에 넣을 필요 없음
                    .build();

            // 2. 실행 및 응답 수신
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            // 파싱
            Post resultPost = gson.fromJson(response.body(), Post.class);

            // 4. 결과 확인
            System.out.println("데이터 삭제 요청");
            System.out.println("응답 HTTP 상태 코드 " + response.statusCode());

            if (response.statusCode() == 200) {
                System.out.println("삭제 응답 결과 확인 : " + resultPost.getTitle());
                System.out.println("삭제 응답 결과 확인 : " + resultPost.getBody());
            } else {
                System.out.println("삭제 실패 : 상태 코드 확인 ");
            }

        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println("DELETE 요청 중에 오류 발생");
            e.printStackTrace(); // 원래 응답 나옴
        }
    }
}
