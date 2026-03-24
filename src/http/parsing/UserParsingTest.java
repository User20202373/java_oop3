package http.parsing;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserParsingTest {

    public static void main(String[] args) {
        String urlString = "https://jsonplaceholder.typicode.com/users/1";
        HttpURLConnection connection;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseHttpCode = connection.getResponseCode();
            System.out.println("통신 성공 여부 확인 : " + responseHttpCode);

            try (BufferedReader reader = new BufferedReader
                    (new InputStreamReader(connection.getInputStream()))) {

                StringBuffer responseBody = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }

                String jsonString = responseBody.toString();

                Gson gson = new Gson();
                User user = gson.fromJson(jsonString, User.class);
                System.out.println(user);
            }

        } catch (Exception e) {
            System.out.println("오류 발생 : " + e.getMessage());
        }
    }
}
