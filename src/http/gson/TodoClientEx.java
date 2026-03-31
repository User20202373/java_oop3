package http.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TodoClientEx {

    public static void main(String[] args) {
        // 통신할 주소
        String urlString = "https://jsonplaceholder.typicode.com/todos";
        HttpURLConnection connection;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            // HTTP 요청 메서드 설정
            connection.setRequestMethod("GET");

            // 연결 요청 및 응답 코드 확인
            int responseHttpCode = connection.getResponseCode();
            System.out.println("통신 성공 여부 확인 : " + responseHttpCode);

            // 응답 본문 추출
            try (BufferedReader reader = new BufferedReader
                    (new InputStreamReader(connection.getInputStream()))) {

                StringBuilder responseBody = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }

                String jsonString = responseBody.toString();

                // Gson을 이용한 JSON Array 파싱
                Gson gson = new Gson();
                TypeToken<List<Todo>> typeToken = new TypeToken<>() {};
                List<Todo> todoList = gson.fromJson(jsonString, typeToken.getType());

                // --- 데이터 처리 및 출력 로직 시작 ---

                int totalCount = todoList.size();     // 전체 개수
                int completedCount = 0;              // 완료된 총 개수
                int printLimit = 5;                  // 출력할 제한 개수
                int currentPrintCount = 0;           // 현재까지 출력된 개수

                // 출력을 위해 미리 담아둘 리스트 혹은 StringBuilder
                StringBuilder printContent = new StringBuilder();

                for (Todo todo : todoList) {
                    // 도전 1: completed가 true인 것만 필터링
                    if (todo.isCompleted()) {
                        completedCount++;

                        // 처음 5개까지만 문자열로 저장
                        if (currentPrintCount < printLimit) {
                            printContent.append(todo.toString()).append("\n");
                            currentPrintCount++;
                        }
                    }
                }

                // 결과 출력
                System.out.println("전체: " + totalCount + "개");
                System.out.println("완료된 Todo: " + completedCount + "개");

                System.out.println("\n[완료된 Todo 처음 " + printLimit + "개]");
                System.out.println(printContent.toString());

                // --- 데이터 처리 및 출력 로직 끝 ---

            }
        } catch (Exception e) {
            System.out.println("통신실패 : " + e.getMessage());
            e.printStackTrace();
        }
    }
}