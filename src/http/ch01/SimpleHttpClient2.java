package http.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//https://jsonplaceholder.typicode.com/todos/10
public class SimpleHttpClient2 {

    public static void main(String[] args) {
        //가짜 서버에 user 10 연결
        //String urlString = "https://jsonplaceholder.typicode.com/todos/10";
        String urlString = "https://jsonplaceholder.typicode.com/posts/1";
        //HTTP 통신하는 클래스
        HttpURLConnection connection = null;
        //1단계 URL 객체 생성
        try {
            URL url = new URL(urlString);
            //2단계 HTTP연결 객체 생성
            connection = (HttpURLConnection) url.openConnection();
            //3단계 요청 방식 설정
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            //4단계 실제요청 전송
            int responseCode = connection.getResponseCode();
            System.out.println("응답 코드 확인 : " + responseCode);

            if (responseCode != 200) {
                System.out.println("요청 실패!");
                return;
            }

            //5단계 응답 본문 읽기
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {

                StringBuffer response = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line).append("\n");
                }
                System.out.println("응답 내용 : ");
                System.out.println(response.toString());
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
