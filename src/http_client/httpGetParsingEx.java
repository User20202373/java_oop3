package http_client;

import com.google.gson.Gson;
import http_client.dtos.Post;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class httpGetParsingEx {

    public static void main(String[] args) {

        //1. HttpClient 와 Gson 준비
        HttpClient client = HttpClient.newHttpClient(); // new HttpClient
        Gson gson = new Gson();

        try {
            //Http 요청 메세지 생성(요청 생성)
            //HttpRequest <-- 객체를 생성해주는 녀석
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                    .GET()
                    .build();

            //3.응답받기
            //client.send(request, HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            //4. 응답 내용 확인(로깅)
            System.out.println("response : " + response);
            System.out.println("response : " + response.body());

            //5. 파싱 처리하기. 오브젝트를 문자로 변환하는 과정 <- 파싱
            Post post = gson.fromJson(response.body(), Post.class);
            System.out.println("==== 파싱 완료 ====");
            System.out.println("게시글 ID: " + post.getId());
            System.out.println("작성자 ID: " + post.getUserId());
            System.out.println("게시글 제목: " + post.getTitle());
            System.out.println("게시글 목록: " + post.getBody());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//        Post post2 = new Post(1, 101, "title", "body..");
//        Post post = Post.builder()
//                .title("게시글 제목")
//                .body("게시글 본문")
//                .id(10)
//                .userId(1001)
//                .build();

    }//end of main
}//end of class
