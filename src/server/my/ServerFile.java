package server.my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5002)) { // 서버 포트 5002번이라는 서버를 열어서 기다림
            Socket clientSocket = serverSocket.accept(); // 클라이언트가 접속할 때까지 멈춰서 기다림, 누군가 접속하면 그 클라이언트와 통신할 수 있는 Socket 객체를 반환함
           //serverSocket → 접속을 받는 역할 // clientSocket → 접속한 클라이언트와 실제 대화하는 역할
            System.out.println("클라이언트 연결");

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//클라이언트가 보낸 바이터 데이터를 문자로 바꾸고 읽기 쉽게 한 줄씩 감쌈
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);//클라이언트에 보낼 데이터를 문자열을 편하게 출력하기 위해 바꿈
            String text;

            while ((text = reader.readLine()) != null){
                String message = reader.readLine(); //클라이언트가 보낸 메세지를 message에 담아서 읽기
                System.out.println("클라이언트 : " + message); //클라이언트 메세지 출력
            }


            writer.println("서버측 메시지"); //서버 측 메세지 보내기

        } catch (IOException e) {
            System.out.println("오류 발생 : " + e.getMessage());
        }
    }
}
