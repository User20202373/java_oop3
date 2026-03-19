package client.my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientFile {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5002)) {


            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            writer.println("난 클라언트");

            String response = reader.readLine();
            System.out.println("서버 측 응답 : " + response);

        } catch (UnknownHostException e) {
            System.out.println("서버측을 알수 없습니다. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("서버측에 연결할 수 없습니다.");
        }
    }
}
