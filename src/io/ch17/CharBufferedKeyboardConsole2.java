package io.ch17;

import java.io.*;

public class CharBufferedKeyboardConsole2 {

    public static void main(String[] args) throws IOException {

//        InputStreamReader isr = new InputStreamReader(System.in);
//        BufferedReader br = new BufferedReader(isr);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //키보드에서 입력된 바이트 데이터를 문자로 바꾼 뒤, 한 줄씩 편하게 읽기 위해 BufferedReader로 감싼 것

//        PrintWriter pw = new PrintWriter(System.out);
//        BufferedWriter bw = new BufferedWriter(pw);
        BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out));

        System.out.print("텍스트 입력 : ");

        String line;
        while ((line = br.readLine()) != null){
            bw.write("받은 값 출력 : " +line);
            bw.newLine();
            bw.flush();
        }



    }
}
