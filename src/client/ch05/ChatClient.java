package client.ch05;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient extends AbstractClient {

    public ChatClient(String name) {
        // 부모 클래스에 사용자 정의 생성자가 있다면 자식 클래스 생성자에서 가장 먼저
        // 부모 생성자를 호출 해야 한다.
        super(name);
    }

    // 서버에 연결 방법만 직접 구현
    @Override
    protected void connectToServer() throws IOException {
        // Socket socket = new Socket("localhost", 5000);
        // setSocket(socket);
        //  부모 클래스 멤버 변수인 socket 에 객체의 주소값을 할당 하지 않으면
        //  nullPointerException 발생 함.
        setSocket(new Socket("localhost", 5000));

    }

    // 실행 하기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("이름을 입력하세요 : ");
        String name = sc.nextLine();

//        ChatClient chatClient = new ChatClient(name);
//        chatClient.run();

        new ChatClient(name).run();

    }
}
