package http.gson;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//https://jsonplaceholder.typicode.com/posts/1
public class PostClient {

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

                Todo todo = gson.fromJson(jsonString, Todo.class);

                System.out.println(todo.getUserId());
                System.out.println(todo.getId());
                System.out.println(todo.getTitle());
                System.out.println(todo.getBody());
                System.out.println(todo.toString());
            }

        } catch (Exception e) {
            System.out.println("통신 실패 : " + e.getMessage());
        }

    }//end of main
}//end of class
