package http.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

//https://jsonplaceholder.typicode.com/posts/1
public class PostClient2 {

    public static void main(String[] args) {
        String urlString = "https://jsonplaceholder.typicode.com/posts";
        HttpURLConnection connection;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseHttpCode = connection.getResponseCode();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuffer responseBody = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);

                }

                String jsonString = responseBody.toString();

                Gson gson = new Gson();
                TypeToken<List<Todo>> typeToken = new TypeToken<>() {};
                List<Todo> todoList = gson.fromJson(jsonString, typeToken.getType());
                System.out.println("전체 개수 : " + todoList.size() + "개");
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println(todoList.get(i));
                }

            }

        } catch (Exception e) {
            System.out.println("통신 실패 : " + e.getMessage());
        }

    }//end of main
}//end of class
