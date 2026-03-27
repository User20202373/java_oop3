package client.ch01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//소켓 통신을 위한 클라이언트 측 도구
public class ClientFile {

    public static void main(String[] args) {

        //클라이언트는 접글할 서버 컴퓨터에 IP 주소와 포트 번호를 알고 있어야 한다
        try (Socket socket = new Socket("192.168.4.21", 5000)) {

            //Socket socket = new Socket("localhost", 5000)
            //위 코드가 실행되는 순간 내부적으로 이미 서버 컴퓨터와 연결을 시도한 상태

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            //writer.write("Hello ~ Server ~~" + "\n"); //개행문자까지 보내야 정상 보내짐
            writer.println("안녕 반가워");

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
        }
    }
}
